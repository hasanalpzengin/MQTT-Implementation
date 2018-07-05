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
    private int ID = -1;
    private InputStream inStream;
    private OutputStream outStream;
    private DataInputStream dinStream;
    private DataOutputStream doutStream;
    private static final int PUBLISH_TYPE = 3;
    private static final int CONNECT_TYPE = 1;
    private static final int HEART_TYPE = 15;
    private static final int SUBSCRIBE_TYPE = 8;
    private static final int PUBLISH_SIZE_POS = 1;
    private String topic;
    
    public ReadThread(Socket socket){
        this.socket = socket;
        ID = socket.getPort();
    }

    @Override
    public void run() {
        try{
            outStream = socket.getOutputStream();
            doutStream = new DataOutputStream(outStream);
            inStream = socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            while(true){
                MessageBuilder builder = new MessageBuilder();
                byte[] data = new byte[1024];
                dinStream.read(data);
                
                System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(data));
                Message message = Decoder.decode(data);
                switch(message.getType()){
                    case PUBLISH_TYPE:{
                        if(message.getQos_level()==0){
                            topic = message.getVariable();
                            int size = (int)data[PUBLISH_SIZE_POS];
                            byte[] publish = new byte[size+2];
                            System.arraycopy(data, 0, publish, 0, publish.length);
                            //doutStream.write(new byte[]{0x01});
                            for(ReadThread readThread : Reader.threads){
                                readThread.doutStream.write(publish);
                            }
                            System.out.println("Published");
                        }
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
                    default:{
                        System.out.println("Unknown Command");
                        break;
                    }
                }
            }
        }catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void open(){
        try {
            dinStream = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        try {
            if(socket!=null) socket.close();
            if(dinStream != null) dinStream.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
