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
    
    public byte[] publish(){
        builder = new MessageBuilder();
        try {
            outStream = Connection.socket.getOutputStream();
            doutStream = new DataOutputStream(outStream);
            //connection message
            this.start();
            inStream = socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            byte[] puback = new byte[2];
            dinStream.read(puback);
            return puback;
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unavailable Host Ip");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void run() {
        while(true){
            try {
                publishMessage = builder.buildPublish(message ,topic);
                doutStream.write(publishMessage);
                doutStream.flush();
                Thread.sleep(1000);
            } catch (IOException | InterruptedException ex) {
                Logger.getLogger(Publish.class.getName()).log(Level.SEVERE, null, ex);
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    
}
