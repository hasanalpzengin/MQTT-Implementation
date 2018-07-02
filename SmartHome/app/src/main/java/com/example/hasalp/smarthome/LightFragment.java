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
import android.widget.ImageButton;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment {
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private ImageButton lightButton;
    private RecyclerAdapter recyclerAdapter;
    private static int currentItem = -1;
    public static final String TOPIC = "house/light/#";
    public static final int QOS = 0;
    public ArrayList<LightAgent> lightAgents = new ArrayList<>();

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

        return view;
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
