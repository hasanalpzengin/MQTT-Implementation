package javaclient;

import mqtt.MQTTClient;
import connection.Publish;
import connection.Connection;
import connection.Subscribe;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
            pub_message.setMessage("0");
            pub_message.setTopic("light");
            pub_message.setQos_level(2);
            //create thread with publish function
            //first parameter established connection
            //second parameter created msg
            //third parameter repeat boolean
            //fourth parameter delay second
            client.publish(connection, pub_message, false, 0).start();
            String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
            System.out.println(time+" : Sent");
            //create Subscribe thread
            //first parameter established connection
            //second parameter subscribed topic
            //third parameter qos level
            Subscribe subscribe = new Subscribe(connection, "light", 2){
                //readHandle function have to be implemented
                //readHandle function's parameter message is recieved message
                @Override
                public void readHandle(Message message) {
                    String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
                    System.out.println(time+" : Recieved");
                }
            };
            //start created thread
            client.startSubscribe(subscribe);
            System.out.println("Subscribed");
        }
    }
    
}
