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
 * @author hasalp
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
    public String topic;
    public int qos=0;
    public boolean isSubscriber = true;
    
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
                dinStream.read(data);
                
                Message message = Decoder.decode(data);
                switch(message.getType()){
                    case PUBLISH_TYPE:{
                        qos = message.getQos_level();
                        topic = message.getVariable();
                        isSubscriber = false;
                        publish(data);
                        break;
                    }
                    case CONNECT_TYPE:{
                        //send connack back
                        byte connack[] = builder.buildConnack(true);
                        doutStream.write(connack);
                        System.out.println("Connected");
                        break;
                    }
                    case SUBSCRIBE_TYPE:{
                        //send suback back
                        topic = message.getVariable();
                        qos = message.getQos_level();
                        isSubscriber = true;
                        byte identifier = message.getFlags()[0];
                        byte qos = message.getQos_level();
                        byte suback[] = builder.buildSuback(qos, identifier);
                        doutStream.write(suback);
                        System.out.println("Subscribed");
                        break;
                    }
                    case HEART_TYPE:{
                        //send puback back and send heart to all subscribers
                        //puback
                        byte puback[] = builder.buildPuback();
                        doutStream.write(puback);
                        //heart
                        for(ReadThread readThread : Reader.threads){
                            readThread.doutStream.write(builder.buildHeart());
                        }
                        break;
                    }
                    case DISCONNECT_TYPE:{
                        close();
                    }
                    default:{
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
        topic = message.getVariable();
        System.out.println("Topic: " + topic);
        System.out.println("Message: " + message.getPayload());
        int size = message.getSize();
        byte[] publish = new byte[size+2];
        System.arraycopy(data, 0, publish, 0, publish.length);
        for(ReadThread readThread : Reader.threads){
            /*
            System.out.println("-------------");
            System.out.println(readThread.topic+" : "+this.topic);
            System.out.println(readThread.qos+" : "+this.qos);
            System.out.println(readThread.isSubscriber);
            */
            if(readThread.isSubscriber && readThread.topic.equalsIgnoreCase(this.topic) && this.qos==readThread.qos){
                System.out.println("Worked");
                readThread.doutStream.write(publish);
                readThread.doutStream.flush();
            }
        }
        System.out.println(message.getQos_level());
        if(message.getQos_level()==0){
            //doutStream.write(new byte[]{0x01});
            System.out.println("Published");
        }else if(message.getQos_level()==1){
            MessageBuilder builder = new MessageBuilder();
            doutStream.write(builder.buildPuback());
            System.out.println("Published QOS1");
        }else if(message.getQos_level()==2){
            MessageBuilder builder = new MessageBuilder();
            doutStream.write(builder.buildPubrec());//rec pub
            byte[] pubrel = new byte[2];
            dinStream.read(pubrel);//rel rec
            if(Decoder.isPubrel(pubrel)){
                doutStream.write(builder.buildPubcomp()); // comp pub
            }
            System.out.println("Published QOS2");
        }
    }
    
    
}
