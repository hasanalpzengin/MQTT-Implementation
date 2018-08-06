package com.example.hasalp.smarthome;

import android.os.AsyncTask;

import connection.Connection;

public class MqttTask extends AsyncTask<String, Integer, String> {
    private String IP;
    private String ID;

    public MqttTask(String IP, String ID) {
        this.IP = IP;
        this.ID = ID;
    }

    @Override
    protected String doInBackground(String... strings) {
        new Connection(IP, ID);
        System.out.println(Connection.isConnected());
        return "";
    }
}
