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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageBuilder;

public class Connection{
    private static InetAddress addr;
    public static final int PORT = 1883;
    //client's socket, important to recieve and send message
    public Socket socket;
    private static MessageBuilder builder;
    private static boolean connected = false;
    private Function function;
    
    public Connection(String ip, String id){
        try {
            //connect to broker's serversocket
            addr = InetAddress.getByName(ip);
            socket = new Socket(addr, PORT);
            socket.setKeepAlive(true);
            //create that object for connect message
            function = new Function(socket);
            //connect message = 1
            byte[] connectMessage = function.builder.buildConnect(id);
            function.doutStream.write(connectMessage);
            function.doutStream.flush();
            //read connack message
            byte[] respond = new byte[4];
            function.dinStream.read(respond);
            //if connack has successfull byte
            //0x20 for connack type 0x00 for success message
            if(respond[0] == 0x20 && respond[2] == 0x00) {
                Connection.connected = true;
                System.out.println("Connection Success");
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unavailable Host Ip");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        //on exit
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                if(Connection.connected){
                    try {
                        function.doutStream.write(builder.buildDisconnect());
                    } catch (IOException ex) {
                        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }
    
    public boolean isConnected(){
        return connected;
    }
}
