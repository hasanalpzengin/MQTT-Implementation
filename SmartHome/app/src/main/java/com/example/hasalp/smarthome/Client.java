package com.example.hasalp.smarthome;

import android.content.Context;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Client {
    private static String clientId;
    private static MqttAndroidClient client;
    private static MqttConnectOptions options;

     public static void startClient(String ip, Context context){
        clientId = MqttClient.generateClientId();
        client = new MqttAndroidClient(context, ip, clientId);
        options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setKeepAliveInterval(60*3);
        options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1);
    }

    public static String getClientId() {
        return clientId;
    }

    public static MqttAndroidClient getClient() {
        return client;
    }

    public static IMqttToken connect(){
        try {
            return client.connect(options);
        } catch (MqttException e) {
            e.printStackTrace();
            return null;
        }
    }
}
