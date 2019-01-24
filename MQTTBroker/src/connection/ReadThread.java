/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Decoder;
import message.Message;
import message.MessageBuilder;

/**
 *
 * 
 */
public class ReadThread extends Thread {
    
    public Socket socket = null;
    private Reader reader = null;
    public int ID;
    private InputStream inStream;
    private OutputStream outStream;
    private DataInputStream dinStream;
    private DataOutputStream doutStream;
    private static final int PUBLISH_TYPE = 3;
    private static final int CONNECT_TYPE = 1;
    private static final int HEART_TYPE = 15;
    private static final int DISCONNECT_TYPE = 14;
    private static final int SUBSCRIBE_TYPE = 8;
    private static final int PUBLISH_SIZE_POS = 1;
    private static int tryCount = 0;
    public String sub_topic, pub_topic;
    public int sub_qos=0, pub_qos=0;
    public boolean isSubscriber = false;
    public boolean isConnected = false;
    
    public ReadThread(Socket socket){
        this.socket = socket;
        ID = socket.getPort();
    }

    @Override
    public void run() {
        try{
            dinStream = new DataInputStream(socket.getInputStream());
            outStream = socket.getOutputStream();
            doutStream = new DataOutputStream(outStream);
            inStream = socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            while(true){
                MessageBuilder builder = new MessageBuilder();
                byte[] data = new byte[1024];
                // read byte data
                dinStream.read(data);
                //decode byte data
                Message message = Decoder.decode(data);
                switch(message.getType()){
                    case PUBLISH_TYPE:{
                        // register client as publisher
                        if(isConnected){
                            pub_qos = message.getQos_level();
                            pub_topic = message.getVariable();
                            // start process
                            publish(data);
                        }
                        break;
                    }
                    case CONNECT_TYPE:{
                        //send connack back
                        byte connack[] = builder.buildConnack(true);
                        doutStream.write(connack);
                        System.out.println("Connected");
                        isConnected = true;
                        break;
                    }
                    case SUBSCRIBE_TYPE:{
                        if(isConnected){
                            // register client as subscriber
                            sub_topic = message.getVariable();
                            sub_qos = message.getQos_level();
                            // for condition of is unsub message recieved
                            isSubscriber = true;
                            byte identifier = message.getFlags()[0];
                            byte qos = message.getQos_level();
                            // build and send suback back
                            byte suback[] = builder.buildSuback(qos, identifier);
                            doutStream.write(suback);
                            System.out.println("Subscribed");
                        }
                        break;
                    }
                    case HEART_TYPE:{
                        // send puback back and send heart to all subscribers
                        // puback
                        byte puback[] = builder.buildPuback();
                        // send puback to heart publisher
                        doutStream.write(puback);
                        // send heart msg to all clients which is connected to broker
                        for(ReadThread readThread : Reader.threads){
                            readThread.doutStream.write(builder.buildHeart());
                        }
                        break;
                    }
                    case DISCONNECT_TYPE:{
                        //close thread
                        close();
                    }
                    default:{
                        // close thread for unexpected msg
                        System.out.println("Unknown Command");
                        close();
                        return;
                    }
                }
            }
        }catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        try {
            Reader.threads.remove(this);
            if(socket!=null) socket.close();
            if(dinStream != null) dinStream.close();
            if(doutStream != null) doutStream.close();
            if(inStream != null) inStream.close();
            if(outStream != null) outStream.close();
            this.interrupt();
            System.out.println(ID+" has disconnect from server");
        } catch (IOException ex) {
            Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void publish(byte[] data) throws IOException {
        Message message = Decoder.decode(data);
        pub_topic = message.getVariable();
        System.out.println("Topic: " + pub_topic);
        System.out.println("Message: " + message.getPayload());
        int size = message.getSize();
        byte[] publish = new byte[size+2];
        System.arraycopy(data, 0, publish, 0, publish.length);
        //send publish msg to all matched clients which is subscribed to same topic and same qos level
        for(ReadThread readThread : Reader.threads){
            if(readThread.isSubscriber && readThread.sub_topic.equalsIgnoreCase(this.pub_topic) && this.pub_qos==readThread.sub_qos && readThread.ID != this.ID){
                System.out.println("Worked");
                readThread.doutStream.write(publish);
                readThread.doutStream.flush();
            }
        }
        //qos progress
        switch (message.getQos_level()) {
            //no progress for qos 0
            case 0:
                System.out.println("Published");
                break;
            //puback has to be sent back for qos 1
            case 1:{
                MessageBuilder builder = new MessageBuilder();
                doutStream.write(builder.buildPuback());
                System.out.println("Published QOS1");
                break;
            }
            //sent pubrec recieve pubrel set pubcomp back for qos 2
            case 2:{
                MessageBuilder builder = new MessageBuilder();
                doutStream.write(builder.buildPubrec());//rec publish
                byte[] pubrel = new byte[2];
                dinStream.read(pubrel);//rel recieve
                if(Decoder.isPubrel(pubrel)){
                    doutStream.write(builder.buildPubcomp()); // comp publish
                }       System.out.println("Published QOS2");
                break;
            }
            default:
                break;
        }
    }
    
    
}
