/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Decoder;
import message.Message;

/**
 *
 * @author hasalp
 */
public class SubscribeRead extends Function implements Runnable {

    public SubscribeRead() {
        super();
    }
    
    @Override
    public void run() {
        try{

            while(true){
                byte[] data = new byte[1024];
                dinStream.read(data);
                Message message = Decoder.decode(data);
                if(message!=null){
                    if(message.getMessage()!= null){
                        System.out.println(message.getMessage());
                    }
                }
                Thread.sleep(100);
            }
        }catch (IOException | InterruptedException ex) {
            Logger.getLogger(SubscribeRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
