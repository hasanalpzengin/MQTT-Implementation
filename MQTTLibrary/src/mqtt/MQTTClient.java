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
 * This class works like builder class.
 * Creating an object with that class will be enough to use library.
 * Steps to use library :
 * 1. Create object from MQTTClient class
 * 2. run connection method
 * 3. run desired function
 * 4. enjoy mqtt protocol
 */
public class MQTTClient {
    private Connection connection = null;
    public Connection connection(String ip, String id){
        //create connection
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                connection = new Connection(ip, id);
            }
        });
        thread.start();
        //print connecting message if connection isn't established
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

    public Connection getConnection() {
        return connection;
    }

    /**
     *  to run publish function user should create a message to send
     *  and give it to function as second parameter
     *  repeat parameter can set the repeated message
     *  delay is exist for repeated messages, it sets sending delay
     **/
    public Thread publish(Connection connection,Message message, boolean isRepeat, int delay){
        //set publish class to run it's thread
        Publish publish = new Publish(connection);
        publish.setMessage(message.getMessage());
        publish.setTopic(message.getTopic());
        publish.setQos(message.getQos_level());
        publish.setRepeat(isRepeat);
        publish.setSecond(delay);
        Thread thread = new Thread(publish);
        return thread;
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
