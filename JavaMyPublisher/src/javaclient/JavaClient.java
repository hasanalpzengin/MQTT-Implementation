package javaclient;

import connection.Connection;
import connection.Publish;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaClient {

    public static void main(String[] args) {
        Connection.connect("192.168.1.2", "Hasan");
        if(Connection.isConnected()){
            //Heart heartPublisher = new Heart();
            //byte puback[] = heartPublisher.heart();
            //if(Decoder.isPubAck(puback)){
                //System.out.println("Heart Sent");
            //}
            Publish publish = new Publish();
            publish.setTopic("test");
            publish.setQos(2);
            while(true){
                try {
                    
                    publish.publish();
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JavaClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
