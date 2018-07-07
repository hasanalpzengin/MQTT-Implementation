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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Decoder;
import message.Message;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 */
public class Reader extends Thread {

    private ServerSocket server = null;
    public static ArrayList<ReadThread> threads;
    private int id = 0;
    
    public Reader(ServerSocket server){
        this.server = server;
        threads = new ArrayList();
    }
    
    @Override
    public void run() {
        while(true){
            try {
                //wait until new connection establish
                addThread(server.accept());
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void addThread(Socket accept) {
        ReadThread thread = new ReadThread(accept);
        thread.ID = id;
        id++;
        thread.open();
        thread.start();
        threads.add(thread);
    }
    
}
