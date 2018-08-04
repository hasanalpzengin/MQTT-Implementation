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
public class HeartRead extends Function implements Runnable {
    
    private byte[] heartMessage;

    public HeartRead() {
        //init IOStreams
        super();
    }

    @Override
    public void run() {
        try {
            while(true){
                heartMessage = builder.buildHeart();
                byte[] heart = new byte[2];
                dinStream.read(heart);
                if(heart[0] == heartMessage[0]){
                    System.out.println("You got a heart");
                }
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Publish.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }

}
