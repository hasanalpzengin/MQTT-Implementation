package javaclient;

import connection.Connection;
import connection.HeartRead;
import connection.Subscribe;
import message.Decoder;
import message.Message;
import mqtt.MQTTClient;

public class JavaClient {

    public static void main(String[] args) {
        MQTTClient client = new MQTTClient();
        Connection connection = client.connection("localhost", "Hasan");
        if(connection.isConnected()){
            
            //topic,qos
            Subscribe subscribe = new Subscribe(connection ,"test",1) {
                @Override
                public void readHandle(Message message) {
                    System.out.println(message.getMessage());
                }
            };
            subscribe.setTopic("test");
            subscribe.setQos(2);
            client.startSubscribe(subscribe);
        }
    }
    
}
