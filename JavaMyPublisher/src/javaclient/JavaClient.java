package javaclient;

import connection.Publish;
import connection.Connection;
import connection.Publish;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaClient {

    public static void main(String[] args) {
        Connection.connect("localhost", "Hasan");
        if(Connection.isConnected()){
            //Heart heartPublisher = new Heart();
            //byte puback[] = heartPublisher.heart();
            //if(Decoder.isPubAck(puback)){
                //System.out.println("Heart Sent");
            //}
            Publish publish = new Publish();
            publish.setTopic("test");
            publish.setMessage("Hello");
            publish.setQos(1);
            publish.setSecond(2);
            publish.autoPublish();
        }
    }
    
}
