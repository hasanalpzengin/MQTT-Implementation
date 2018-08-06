package javaclient;

import mqtt.MQTTClient;
import connection.Publish;
import connection.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Message;


public class JavaClient {

    public static void main(String[] args) {
        MQTTClient client = new MQTTClient();
        Connection connection = client.connection("localhost", "Hasan");
        if(connection.isConnected()){
            //Heart heartPublisher = new Heart();
            //byte puback[] = heartPublisher.heart();
            //if(Decoder.isPubAck(puback)){
                //System.out.println("Heart Sent");
            //}
            Message message = new Message();
            message.setMessage("1");
            message.setTopic("light");
            message.setQos_level(0);
            client.publish(connection, message, true, 4);
        }
    }
    
}
