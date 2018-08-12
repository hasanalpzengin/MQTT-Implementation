package com.example.hasalp.smarthome;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import connection.Connection;
import connection.Publish;
import connection.Subscribe;
import message.Message;
import mqtt.MQTTClient;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager layoutManager;
    private RecyclerView lightRecyclerView;
    private static ImageButton lightButton;
    private FloatingActionButton voiceButton;
    private RecyclerAdapter recyclerAdapter;
    private MQTTClient client;
    private Connection connection;
    private static ArrayList<LightAgent> lightAgents;
    private static final int SPEECH_CODE = 100;
    private static int current = -1;

    public static void setCurrent(int current) {
        MainActivity.current = current;
        if (!lightAgents.get(current).isStatus()){
            lightButton.setImageResource(R.drawable.light_on);
        }else{
            lightButton.setImageResource(R.drawable.light_off);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        client = new MQTTClient();
        //wifi hotspot
        //connection = client.connection("10.42.0.1","Hasan");
        connection = client.connection("192.168.56.1", "Hasan");
        Subscribe subscribe = new Subscribe(connection, "light", 0) {
            @Override
            public void readHandle(Message message) {
                //remove non numeric characters
                String strMessage = message.getMessage();
                strMessage = strMessage.replaceAll("[^\\d.]", "");
                message.setMessage(strMessage);
                //update input
                if(strMessage.length()>0) {
                    update(message);
                }
            }
        };
        client.startSubscribe(subscribe);
    }

    private void update(Message message) {
        int i=0;
        for (LightAgent agent : lightAgents) {
            if (agent.getTopic().equals(message.getTopic())) {
                if((Integer.parseInt(message.getMessage().substring(0,1))==1) != agent.isStatus()) {
                    changeStatus(i);
                    return;
                }else{
                    //same type don't need to change
                    return;
                }
            }
            i++;
        }
        LightAgent lightAgent = new LightAgent();
        lightAgent.setTopic(message.getTopic());
        lightAgent.setStatus(Integer.parseInt(message.getMessage().substring(0,1))==1);
        lightAgents.add(lightAgent);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerAdapter.notifyDataSetChanged();
                //if new added agent is the first agent
                if (lightAgents.size()==1){
                    current = 0;
                    if (!lightAgents.get(current).isStatus()){
                        lightButton.setImageResource(R.drawable.light_on);
                    }else{
                        lightButton.setImageResource(R.drawable.light_off);
                    }
                }
            }
        });
    }

    private void init() {
        lightButton = findViewById(R.id.lightButton);
        voiceButton = findViewById(R.id.voiceButton);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerAdapter = new RecyclerAdapter();
        lightRecyclerView = findViewById(R.id.lightRecyclerView);
        lightRecyclerView.setLayoutManager(layoutManager);
        lightRecyclerView.setHasFixedSize(true);
        lightRecyclerView.setAdapter(recyclerAdapter);
        lightAgents = new ArrayList<>();

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceCommand();
            }
        });

        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current!=-1) {
                    LightAgent lightAgent = changeStatus(current);
                    if (!lightAgents.get(current).isStatus()) {
                        lightButton.setImageResource(R.drawable.light_on);
                    } else {
                        lightButton.setImageResource(R.drawable.light_off);
                    }
                    publish(lightAgent);
                }
            }
        });
    }

    private LightAgent changeStatus(int pos){
        if (current != -1) {
            LightAgent selectedAgent = lightAgents.get(pos);
            //change status
            selectedAgent.setStatus(!selectedAgent.isStatus());
            lightAgents.set(pos, selectedAgent);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerAdapter.notifyDataSetChanged();
                }
            });
            return selectedAgent;
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.selectLight), Toast.LENGTH_LONG).show();
        }
        return null;
    }

    private void voiceCommand() {
        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        voiceIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, SPEECH_CODE);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say 'Light ON' or 'Light OFF'");
        try{
            startActivityForResult(voiceIntent, SPEECH_CODE);
        }catch(ActivityNotFoundException e){
            Log.e("Speech Activity Error", e.getMessage());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case SPEECH_CODE:{
                if(resultCode == RESULT_OK && null != data){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.get(0).equalsIgnoreCase("Light on")){
                        LightAgent currentAgent = lightAgents.get(current);
                        currentAgent.setStatus(true);
                        lightAgents.set(current, currentAgent);
                        publish(currentAgent);
                        Log.d("Light Value", String.valueOf(1));
                    }else if(result.get(0).equalsIgnoreCase("Light off")){
                        LightAgent currentAgent = lightAgents.get(current);
                        currentAgent.setStatus(false);
                        lightAgents.set(current, currentAgent);
                        publish(currentAgent);
                        Log.d("Light Value", String.valueOf(0));
                    }
                }
                break;
            }
        }
    }

    public void publish(LightAgent lightAgent){
        Message publishMessage = new Message();
        String message = lightAgent.isStatus() ? "1" : "0";
        publishMessage.setMessage(message);
        publishMessage.setTopic(lightAgent.getTopic()+"/change");
        publishMessage.setQos_level(0);

        Thread publishThread = client.publish(connection, publishMessage, false, 0);
        publishThread.start();
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<LightRecyclerViewHolder>{

        @NonNull
        @Override
        public LightRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //inflate
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.light_card, parent, false);
            return new LightRecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LightRecyclerViewHolder holder, int position) {
            LightAgent lightAgent = lightAgents.get(position);

            holder.setAgentName(lightAgent.getTopic());
            holder.setStatus(lightAgent.isStatus());
            holder.setPos(position);
        }

        @Override
        public int getItemCount() {
            return lightAgents.size();
        }
    }
}
