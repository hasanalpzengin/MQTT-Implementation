/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import static connection.Connection.PORT;
import static connection.Connection.socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Decoder;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 */
public class Publish extends Thread {
    
    private OutputStream outStream;
    private DataOutputStream doutStream;
    private InputStream inStream;
    private DataInputStream dinStream;
    private MessageBuilder builder;
    private byte[] publishMessage;
    private String message, topic;
    private int second = 10;
    private int qos = 0;
    
    public void publish(){
        if(!message.isEmpty() && !topic.isEmpty()){
            builder = new MessageBuilder();
            try {
                outStream = Connection.socket.getOutputStream();
                doutStream = new DataOutputStream(outStream);
                //connection message
                this.start();
                inStream = socket.getInputStream();
                dinStream = new DataInputStream(inStream);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Unavailable Host Ip");
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        try {
            if(qos==0){
                publishMessage = builder.buildPublish(message ,topic, qos);
                doutStream.write(publishMessage);
                doutStream.flush();
                System.out.println("Publish Success QoS0");
            }else if(qos==1){
                publishMessage = builder.buildPublish(message ,topic, qos);
                doutStream.write(publishMessage);
                doutStream.flush();
                byte[] puback = new byte[2];
                dinStream.read(puback);
                if(Decoder.isPubAck(puback)){
                    System.out.println("Publish Success QoS1");
                }
            }else{
                publishMessage = builder.buildPublish(message ,topic, qos);
                doutStream.write(publishMessage);
                doutStream.flush();
                byte[] pubrec = new byte[2];
                dinStream.read(pubrec);
                if(Decoder.isPubrec(pubrec)){
                    doutStream.write(builder.buildPubrel());
                    doutStream.flush();
                    byte[] pubcomp = new byte[2];
                    dinStream.read(pubcomp);
                    if(Decoder.isPubcomp(pubcomp)){
                        System.out.println("Publish Success QoS2");
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Publish.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getQos(){
        return qos;
    }
    
    public void setQos(int qos){
        this.qos = qos;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    } 
}
