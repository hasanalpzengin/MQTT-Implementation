package com.example.hasalp.smarthome;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TemperatureRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ImageView statusImage;
    private TextView agentName;
    private TextView tempText;
    private int position;
    private int status;

    public TemperatureRecyclerViewHolder(View itemView) {
        super(itemView);
        statusImage = itemView.findViewById(R.id.status);
        agentName = itemView.findViewById(R.id.agentName);
        tempText = itemView.findViewById(R.id.temperatureView);

        itemView.setOnClickListener(this);
    }

    public String getAgentName(){
        return agentName.getText().toString();
    }

    public void setStatus(int status){
        this.status = status;
        if (status<21){
            statusImage.setImageResource(R.drawable.temp_cold);
        }else{
            statusImage.setImageResource(R.drawable.temp_hot);
        }
        tempText.setText(String.valueOf(status));
    }

    public int getStatus(){
        return status;
    }

    public void setAgentName(String name){
        agentName.setText(name);
    }

    public int getPos() {
        return position;
    }

    public void setPos(int position) {
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        TemperatureFragment.setCurrent(position);
    }
}
