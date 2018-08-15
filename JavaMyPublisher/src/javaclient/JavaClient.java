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
        //define object for library
        MQTTClient client = new MQTTClient();
        //create connection
        Connection connection = client.connection("localhost", "Hasan");
        if(connection.isConnected()){
            //create message to publish
            Message pub_message = new Message();
            pub_message.setMessage(String.valueOf(status));
            pub_message.setTopic("light");
            pub_message.setQos_level(0);
            //create thread with publish function
            //first parameter established connection
            //second parameter created msg
            //third parameter repeat boolean
            //fourth parameter delay second
            Thread publish = client.publish(connection, pub_message, true, 4);
            publish.start();
            //create Subscribe thread
            //first parameter established connection
            //second parameter subscribed topic
            //third parameter qos level
            Subscribe subscribe = new Subscribe(connection, "light/change", 0){
                //readHandle function have to be implemented
                //readHandle function's parameter message is recieved message
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
            //start created thread
            client.startSubscribe(subscribe);
        }
    }
    
}
