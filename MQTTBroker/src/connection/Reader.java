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
 * @author hasalp
 * That thread is for managing client connections
 */
public class Reader extends Thread {
    //pass server socket from Connection class to handle new connections
    private ServerSocket server = null;
    //threads arraylist is for storing client connections.
    public static ArrayList<ReadThread> threads;
    //next client's (thread's) client id
    private int id = 0;
    
    public Reader(ServerSocket server){
        //init variables
        this.server = server;
        threads = new ArrayList();
    }
    
    @Override
    public void run() {
        //listen for new client connection forever.
        while(true){
            try {
                //wait until new connection establish
                addThread(server.accept());
            } catch (IOException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    //after new connection recieved add new ReadThread
    private void addThread(Socket accept) {
        //that thread listens the connection of client.
        ReadThread thread = new ReadThread(accept);
        thread.ID = id;
        id++;
        //start thread
        thread.start();
        //add created client (thread) to arraylist
        threads.add(thread);
    }
    
}
