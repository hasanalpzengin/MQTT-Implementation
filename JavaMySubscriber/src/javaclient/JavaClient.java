package javaclient;

import connection.Connection;
import connection.HeartRead;
import connection.Subscribe;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import message.Decoder;
import message.Message;
import mqtt.MQTTClient;

public class JavaClient {

    public static void main(String[] args) {
        MQTTClient client = new MQTTClient();
        Connection connection = client.connection("localhost", "Hasan");
        if(connection.isConnected()){
            //topic,qos
            Subscribe subscribe = new Subscribe(connection ,"light",2) {
                @Override
                public void readHandle(Message message) {
                    String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());
                    System.out.println(time+" : Recieved");
                    System.out.println(message.getMessage());
                    Message pub_message = new Message();
                    pub_message.setMessage("0");
                    pub_message.setTopic("light");
                    pub_message.setQos_level(2);
                    client.publish(connection, pub_message, false, 0).start();
                    time = new SimpleDateFormat("yyyy/MM/dd HH:mms:s.SSS").format(Calendar.getInstance().getTime());
                    System.out.println(time+" : Sent");
                }
            };
            client.startSubscribe(subscribe);
        }
    }
    
}
