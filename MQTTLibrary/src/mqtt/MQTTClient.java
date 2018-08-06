package mqtt;


import connection.Connection;
import connection.Heart;
import connection.HeartRead;
import connection.Publish;
import connection.Subscribe;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hasalp
 */
public class MQTTClient {
    private Connection connection = null;
    public Connection connection(String ip, String id){
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                connection = new Connection(ip, id);
            }
        });
        thread.start();
        while(connection == null){
            try {
                System.out.println("Connecting...");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MQTTClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        while(!connection.isConnected()){
            try {
                System.out.println("Connecting...");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MQTTClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    public Publish publish(Connection connection,Message message, boolean isRepeat, int delay){
        Publish publish = new Publish(connection);
        publish.setMessage(message.getMessage());
        publish.setTopic(message.getTopic());
        publish.setQos(message.getQos_level());
        publish.setRepeat(isRepeat);
        publish.setSecond(delay);
        (new Thread(publish)).start();
        return publish;
    }
    
    public Heart heart(Connection connection){
        Heart heart = new Heart(connection);
        (new Thread(heart)).start();
        return heart;
    }
    
    public HeartRead heartRead(Connection connection){
        HeartRead heartRead = new HeartRead(connection);
        return heartRead;
    }
    
    public Subscribe startSubscribe(Subscribe subscribe){
        (new Thread(subscribe)).start();
        return subscribe;
    }
}
