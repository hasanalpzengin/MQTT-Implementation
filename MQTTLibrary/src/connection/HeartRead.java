/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

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
 * 
 * This class extends Function class and implements Runnable
 * Function class gains privileges to publish or recieve message
 * Runnable used to run function as Thread
 */
public class HeartRead extends Function implements Runnable {
    
    private byte[] heartMessage;

    public HeartRead(Connection connection) {
        //init IOStreams
        super(connection.socket);
    }

    @Override
    public void run() {
        try {
            //create heart message for check condition
            heartMessage = builder.buildHeart();
            while(true){
                //recieve message
                byte[] heart = new byte[2];
                dinStream.read(heart);
                //is heart
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
