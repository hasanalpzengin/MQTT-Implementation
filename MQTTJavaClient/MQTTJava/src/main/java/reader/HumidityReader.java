/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reader;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 *
 * @author hasalp
 */
public class HumidityReader extends Reader {
    protected String topic = "house/room1/humidity", lastValue, clientID;
    protected float interval;
    MqttClient client;
    
    @Override
    public void setReader(float interval) {
        this.interval = interval;
        this.clientID = MqttClient.generateClientId();
        try {
            String brokerAddress = init.MQTTJava.BROKER_IP+":"+init.MQTTJava.BROKER_PORT;
            //client setup with options
            client = new MqttClient(brokerAddress, clientID);
            System.out.println(brokerAddress);
            System.out.println("Connecting to broker: "+brokerAddress);
            client.connect();
            client.setCallback(new mqtt.SubscriberCallback());
        } catch (MqttException ex) {
            Logger.getLogger(HumidityReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void start() {
        try {
            client.subscribe(topic);
        } catch (MqttException ex) {
            Logger.getLogger(HumidityReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void stop(){
        try {
            client.unsubscribe(topic);
        } catch (MqttException ex) {
            Logger.getLogger(TemperatureReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }

    @Override
    public String getLastValue() {
        return lastValue;
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public float getInterval() {
        return interval;
    }

    @Override
    public String getClientID() {
        return clientID;
    }
}
