package com.example.hasalp.smarthome;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LightRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ImageView statusImage;
    private TextView agentName;
    private int position;
    private boolean status=false;

    public LightRecyclerViewHolder(View itemView) {
        super(itemView);
        statusImage = itemView.findViewById(R.id.status);
        agentName = itemView.findViewById(R.id.agentName);
        itemView.setOnClickListener(this);
    }

    public String getAgentName(){
        return agentName.getText().toString();
    }

    public void setStatus(boolean status){
        this.status = status;
        if (status){
            statusImage.setImageResource(R.drawable.light_on);
        }else{
            statusImage.setImageResource(R.drawable.light_off);
        }
    }

    public boolean getStatus(){
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
        LightFragment.setCurrent(position);
    }
}
