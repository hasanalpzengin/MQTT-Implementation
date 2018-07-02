package com.example.hasalp.smarthome;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TemperatureFragment extends Fragment {


    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ImageButton setButton;
    private ImageView temperatureImage;
    private EditText tempEditText;
    private SeekBar tempBar;
    private RecyclerAdapter recyclerAdapter;
    private static int currentItem = -1;
    public static final String TOPIC = "house/temperature/#";
    public static final int QOS = 0;
    public ArrayList<TemperatureAgent> temperatureAgents = new ArrayList<>();

    private void updateValue(){
        if (currentItem != -1) {
            if (temperatureAgents.get(currentItem).getStatus() < 21) {
                temperatureImage.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        temperatureImage.setImageResource(R.drawable.temp_cold);
                        tempEditText.setText(String.valueOf(temperatureAgents.get(currentItem).getStatus()));
                    }
                });
            } else {
                temperatureImage.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        temperatureImage.setImageResource(R.drawable.temp_hot);
                        tempEditText.setText(String.valueOf(temperatureAgents.get(currentItem).getStatus()));
                    }
                });
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void publish(int pos, int value){
        TemperatureAgent temperatureAgent = temperatureAgents.get(pos);
        String newValue = String.valueOf(value);
        byte[] payload = newValue.getBytes();
        MqttMessage newMessage = new MqttMessage();
        newMessage.setPayload(payload);
        newMessage.setQos(0);
        newMessage.setRetained(false);
        temperatureAgent.setStatus(value);
        temperatureAgents.set(pos, temperatureAgent);
        try {
            Client.getClient().publish(temperatureAgent.getTopic()+"/change", newMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private int getIndex(String topic){
        //scan for device
        for (int i=0; i<temperatureAgents.size(); i++){
            if (topic.contentEquals(temperatureAgents.get(i).getTopic())){
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
                    Integer status = Integer.parseInt(new String(message.getPayload(), "UTF-8"));
                    //if not null
                    if (!topic.contains("change")) {
                        Log.d("Value Fetched", String.valueOf(status));
                        int index = getIndex(topic);
                        if (index == -1) {
                            TemperatureAgent temperatureAgent = new TemperatureAgent();
                            temperatureAgent.setTopic(topic);
                            temperatureAgent.setStatus(status);
                            temperatureAgent.setType("Temperature");
                            temperatureAgents.add(temperatureAgent);
                            //if this is first item set current
                            setCurrent(0);
                            Log.d("Update","Success");
                            if (recyclerAdapter != null) {
                                recyclerAdapter.notifyItemInserted(currentItem);
                                updateValue();
                            }
                        } else {
                            temperatureAgents.get(index).setStatus(status);
                            if (recyclerAdapter != null) {
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
        if (temperatureAgents.size()!=0) {
            int newValue = Integer.parseInt(tempEditText.getText().toString());
            publish(currentItem, newValue);
            updateValue();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        recyclerAdapter = new RecyclerAdapter();
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView = view.findViewById(R.id.temperatureRecycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(recyclerAdapter);
        temperatureImage = view.findViewById(R.id.temperatureImage);
        tempBar = view.findViewById(R.id.tempBar);
        tempBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tempEditText.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getContext(), getString(R.string.pressChange), Toast.LENGTH_SHORT).show();
            }
        });
        tempEditText = view.findViewById(R.id.temperatureText);
        setButton = view.findViewById(R.id.setButton);
        setButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStatus();
            }
        });

        return view;
    }

    private class RecyclerAdapter extends RecyclerView.Adapter<TemperatureRecyclerViewHolder>{

        @NonNull
        @Override
        public TemperatureRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //inflate
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.temperature_card, parent, false);
            return new TemperatureRecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TemperatureRecyclerViewHolder holder, int position) {
            TemperatureAgent temperatureAgent = temperatureAgents.get(position);

            holder.setAgentName(temperatureAgent.getTopic());
            holder.setStatus(temperatureAgent.getStatus());
            holder.setPos(position);
        }

        @Override
        public int getItemCount() {
            return temperatureAgents.size();
        }
    }

}
