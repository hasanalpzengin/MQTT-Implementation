/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection {
    // 1883 is reserved MQTT port
    public static final int PORT = 1883;
    // ServerSocket to create socket
    public static ServerSocket serverSocket;
    // Boolean value to record connect status
    public static boolean connected = false;
    
    //init function
    public ServerSocket createServer(int port){
        try {
            //init server socket and start server
            serverSocket = new ServerSocket(port);
            return serverSocket;
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unavailable Host Ip");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
