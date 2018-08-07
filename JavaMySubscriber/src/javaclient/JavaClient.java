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
            Subscribe subscribe = new Subscribe(connection ,"light",0) {
                @Override
                public void readHandle(Message message) {
                    System.out.println(message.getMessage());
                }
            };
            client.startSubscribe(subscribe);
        }
    }
    
}
