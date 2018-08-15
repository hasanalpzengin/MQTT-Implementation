/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Decoder;
import message.Message;

/**
 *
 * @author hasalp
 * 
 * This class extends Function class and implements Runnable
 * Function class gains privileges to publish or recieve message
 * Runnable used to run function as Thread
 */
public abstract class Subscribe extends Function implements Runnable {

    private byte[] subscribeMessage;
    private String topic;
    private int qos;
    
    public Subscribe(Connection connection ,String topic, int qos) {
        //init IO objects
        super(connection.socket);
        //subscribe request
        try {
            this.qos = qos;
            this.topic = topic;
            //create subscribe request message with desired topic and qos level
            subscribeMessage = builder.buildSubscribe(qos ,topic);
            (new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        //send subscribe request
                        doutStream.write(subscribeMessage);
                        doutStream.flush();
                    } catch (IOException ex) {
                        Logger.getLogger(Subscribe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            })).start();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Subscribe.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public void run() {
        try{
            while(true){
                //read message
                byte[] data = new byte[1024];
                dinStream.read(data);
                //decode fetched message
                Message message = Decoder.decode(data);
                if(message!=null){
                    if(message.getMessage()!= null){
                        /**
                         * readHandle function have to overrided by user so parsed message can be usable by developer
                        **/
                        readHandle(message);
                    }
                }
                Thread.sleep(100);
            }
        }catch (IOException | InterruptedException ex) {
            Logger.getLogger(Subscribe.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQos() {
        return qos;
    }

    public void setQos(int qos) {
        this.qos = qos;
    }
    //abstract function has to be initiliazed when Subscribe object created.
    public abstract void readHandle(Message message);
    
}
