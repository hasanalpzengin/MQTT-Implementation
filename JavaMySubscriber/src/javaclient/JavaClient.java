package javaclient;

import connection.Connection;
import connection.HeartRead;
import connection.Subscribe;
import message.Decoder;

public class JavaClient {

    public static void main(String[] args) {
        Connection.connect("localhost", "Hasan");
        if(Connection.isConnected()){
            //HeartRead reader = new HeartRead();
            //reader.heartread();
            Subscribe subscribe = new Subscribe();
            subscribe.setTopic("test");
            subscribe.setQos(2);
            subscribe.subscribe();
        }
    }
    
}
