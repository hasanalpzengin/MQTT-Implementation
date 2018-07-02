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

public class Connection {
    private static InetAddress addr;
    public static final int PORT = 1883;
    private static OutputStream outStream;
    private static DataOutputStream doutStream;
    private static InputStream inStream;
    private static DataInputStream dinStream;
    public static Socket socket;
    private static MessageBuilder builder;
    public static boolean connected = false;
    
    public static byte[] connect(String ip, String id){
        builder = new MessageBuilder();
        try {
            addr = InetAddress.getByName(ip);
            socket = new Socket(addr, PORT);
            socket.setKeepAlive(true);
            outStream = socket.getOutputStream();
            doutStream = new DataOutputStream(outStream);
            //connection message
            byte[] connectMessage = builder.buildConnect(id);
            doutStream.write(connectMessage);
            doutStream.flush();

            inStream = socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            byte[] respond = new byte[4];
            dinStream.read(respond);
            return respond;
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Unavailable Host Ip");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
