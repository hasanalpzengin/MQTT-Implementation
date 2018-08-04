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
import message.Decoder;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 */
public class Heart extends Function implements Runnable {
    
    private byte[] heartMessage;
    
    public Heart(){
        super();
        builder = new MessageBuilder();
        try {
            byte[] puback = new byte[2];
            dinStream.read(puback);
            if(Decoder.isPubAck(puback)){
                System.out.println("Sending Success");
            }else{
                System.out.println("Sending Failed");
            }
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
            heartMessage = builder.buildHeart();
            doutStream.write(heartMessage);
            doutStream.flush();
            Thread.sleep(1000);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Publish.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    
    
}
