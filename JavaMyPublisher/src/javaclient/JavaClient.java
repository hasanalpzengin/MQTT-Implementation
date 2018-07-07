package javaclient;

import connection.Connection;
import connection.Heart;
import connection.Publish;
import message.Decoder;

public class JavaClient {

    public static void main(String[] args) {
        Connection.connect("::1", "Hasan");
        if(Connection.isConnected()){
            //Heart heartPublisher = new Heart();
            //byte puback[] = heartPublisher.heart();
            //if(Decoder.isPubAck(puback)){
                //System.out.println("Heart Sent");
            //}
            Publish publish = new Publish();
            publish.setMessage("Hello");
            publish.setTopic("test");
            publish.setQos(2);
            publish.publish();
        }
    }
    
}
