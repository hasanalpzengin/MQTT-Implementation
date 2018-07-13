/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import static connection.Connection.socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 */
public class Subscribe extends Thread {
    
    private OutputStream outStream;
    private DataOutputStream doutStream;
    private InputStream inStream;
    private DataInputStream dinStream;
    private MessageBuilder builder;
    private byte[] subscribeMessage;
    private String topic;
    private int qos;
    
    public void subscribe(){
        builder = new MessageBuilder();
        try {
            outStream = Connection.socket.getOutputStream();
            doutStream = new DataOutputStream(outStream);
            inStream = socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            //connection message
            this.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unavailable Host Ip");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            subscribeMessage = builder.buildSubscribe(qos ,topic);
            doutStream.write(subscribeMessage);
            doutStream.flush();
            SubscribeRead read = new SubscribeRead();
            read.start();
        } catch (IOException ex) {
            Logger.getLogger(AutoPublish.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
}
