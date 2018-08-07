/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import static connection.Connection.PORT;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Decoder;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 */
public class Publish extends Function implements Runnable {
    
    private byte[] publishMessage;
    private static String message = null, topic = null;
    private int second = 10;
    private int qos = 0;
    private boolean repeat = true;

    public Publish(Connection connection) {
        super(connection.socket);
    }

    @Override
    public void run() {
        if(topic==null){
            System.out.println("Null Topic is not allowed");
            return;
        }else if(message==null){
            System.out.println("Null Message is not allowed");
            return;
        }
        while(true){
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
                Thread.sleep(1000*second);
                if(!repeat){
                    return;
                }
            } catch (IOException | InterruptedException ex) {
                System.out.println("Thread Killed");
                return;
            }
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

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
    
    public void setRepeat(boolean repeat){
        this.repeat = repeat;
    }
    
    public boolean getRepeat(){
        return this.repeat;
    }
    
}
