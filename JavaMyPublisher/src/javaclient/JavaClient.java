package javaclient;

import mqtt.MQTTClient;
import connection.Publish;
import connection.Connection;
import connection.Subscribe;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Message;


public class JavaClient {

    public static int status = 0;
    
    public static void main(String[] args) {
        MQTTClient client = new MQTTClient();
        Connection connection = client.connection("localhost", "Hasan");
        if(connection.isConnected()){
            //Heart heartPublisher = new Heart();
            //byte puback[] = heartPublisher.heart();
            //if(Decoder.isPubAck(puback)){
                //System.out.println("Heart Sent");
            //}
            Message pub_message = new Message();
            pub_message.setMessage(String.valueOf(status));
            pub_message.setTopic("light");
            pub_message.setQos_level(0);
            Thread publish = client.publish(connection, pub_message, true, 4);
            publish.start();
            //subscribe thread
            Subscribe subscribe = new Subscribe(connection, "light/change", 0){
                @Override
                public void readHandle(Message message) {
                    if(Integer.parseInt(message.getMessage().substring(0, 1))!=status){
                        System.out.println("Message Fetched: "+message.getMessage());
                        status = Integer.parseInt(message.getMessage().substring(0, 1));
                        pub_message.setMessage(message.getMessage().substring(0, 1));
                        publish.interrupt();
                        Thread publish = client.publish(connection, pub_message, true, 4);
                        publish.start();
                    }
                }
            };
            client.startSubscribe(subscribe);
        }
    }
    
}
