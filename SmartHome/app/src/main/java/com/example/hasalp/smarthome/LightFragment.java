package com.example.hasalp.smarthome;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment {
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ImageButton lightButton;
    private FloatingActionButton voiceButton;
    private RecyclerAdapter recyclerAdapter;
    private static int currentItem = -1;
    public static final String TOPIC = "house/light/#";
    public static final int QOS = 0;
    public ArrayList<LightAgent> lightAgents = new ArrayList<>();
    private static final int SPEECH_CODE = 100;

    private void updateImage(){
        if (currentItem!=-1) {
            if (lightAgents.get(currentItem).getStatus()) {
                lightButton.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        lightButton.setImageResource(R.drawable.light_on);
                    }
                });
            } else {
                lightButton.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        lightButton.setImageResource(R.drawable.light_off);
                    }
                });
            }
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void publish(int pos, boolean value){
        LightAgent lightAgent = lightAgents.get(pos);
        String newValue = value ? "1" : "0";
        byte[] payload = newValue.getBytes();
        MqttMessage newMessage = new MqttMessage();
        newMessage.setPayload(payload);
        newMessage.setQos(0);
        newMessage.setRetained(false);
        lightAgent.setStatus(value);
        lightAgents.set(pos, lightAgent);
        try {
            Client.getClient().publish(lightAgent.getTopic()+"/change", newMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private int getIndex(String topic){
        //scan for device
        for (int i=0; i<lightAgents.size(); i++){
            if (topic.contentEquals(lightAgents.get(i).getTopic())){
                return i;
            }
        }
        return -1;
    }

    public void startSubscribe(){
        try {
            Client.getClient().subscribe(TOPIC, QOS, new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    Integer value = Integer.parseInt(new String(message.getPayload(), "UTF-8"));
                    boolean status = (value != 0);
                    Log.d("Message Arrived", String.valueOf(value));
                    //if not null
                    if (!topic.contains("change")) {
                        int index = getIndex(topic);
                        if (index == -1) {
                            LightAgent lightAgent = new LightAgent();
                            lightAgent.setTopic(topic);
                            lightAgent.setStatus(status);
                            lightAgent.setType("Light");
                            lightAgents.add(lightAgent);
                            //if this is first item set current
                            setCurrent(0);
                            if (lightButton!=null) {
                                updateImage();
                                recyclerAdapter.notifyItemInserted(currentItem);
                            }
                        } else {
                            lightAgents.get(index).setStatus(status);
                            if (lightButton!=null) {
                                updateImage();
                                recyclerAdapter.notifyItemChanged(index);
                            }
                        }
                    }

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            Log.d("Subscribe Thread","Failed");
        }
    }

    public static void setCurrent(int newPos){
        currentItem = newPos;
    }

    private void changeStatus(){
        if (lightAgents.size()!=0) {
            boolean newValue = !lightAgents.get(currentItem).getStatus();
            publish(currentItem, newValue);
            updateImage();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_light, container, false);
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerAdapter = new RecyclerAdapter();
        recyclerView = view.findViewById(R.id.lightRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);
        lightButton = view.findViewById(R.id.lightButton);
        lightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus();
            }
        });
        voiceButton = view.findViewById(R.id.voiceButton);

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceCommand();
            }
        });

        return view;
    }

    private void voiceCommand() {
        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
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
                        publish(currentItem, true);
                    }else if(result.get(0).equalsIgnoreCase("Light off")){
                        publish(currentItem, false);
                    }
                }
            }
        }
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<LightRecyclerViewHolder>{

        @NonNull
        @Override
        public LightRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //inflate
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.light_card, parent, false);
            return new LightRecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LightRecyclerViewHolder holder, int position) {
            LightAgent lightAgent = lightAgents.get(position);

            holder.setAgentName(lightAgent.getTopic());
            holder.setStatus(lightAgent.getStatus());
            holder.setPos(position);
        }

        @Override
        public int getItemCount() {
            return lightAgents.size();
        }
    }
}
