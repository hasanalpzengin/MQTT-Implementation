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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageBuilder;

public class Connection {
    private static InetAddress addr;
    public static final int PORT = 1883;
    private static InputStream inStream;
    private static DataInputStream dinStream;
    public static ServerSocket serverSocket;
    public static Socket socket;
    private static MessageBuilder builder;
    public static boolean connected = false;
    
    public static void createServer(int port){
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unavailable Host Ip");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}