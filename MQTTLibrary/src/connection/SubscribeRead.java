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

/**
 *
 * @author hasalp
 */
public class SubscribeRead extends Thread {

    private InputStream inStream;
    private DataInputStream dinStream;
    
    @Override
    public void run() {
        try{
            inStream = Connection.socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            while(true){
                byte[] data = new byte[999];
                dinStream.read(data);
                if(data[0] == 0x30){
                    System.out.println("Fetched");
                }
                Thread.sleep(100);
            }
        }catch (IOException | InterruptedException ex) {
            Logger.getLogger(SubscribeRead.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
