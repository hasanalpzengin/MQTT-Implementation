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
    private Socket socket = null;
    private Reader reader = null;
    private int ID = -1;
    private InputStream inStream;
    private OutputStream outStream;
    private DataInputStream dinStream;
    private DataOutputStream doutStream;
    private static final int PUBLISH_TYPE = 3;
    private static final int CONNECT_TYPE = 1;
    private static final int SUBSCRIBE_TYPE = 8;
    
    public ReadThread(Socket socket){
        this.reader = reader;
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
                        doutStream.write(data);
                        System.out.println("Published");
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
                        byte identifier = message.getFlags()[0];
                        byte qos = message.getQos_level();
                        byte suback[] = builder.buildSuback(qos, identifier);
                        doutStream.write(suback);
                        System.out.println("Subscribed");
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
