/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttlibrary;

import java.io.UnsupportedEncodingException;
import message.Encoder;
import connection.Connection;
import connection.Reader;
import java.net.ServerSocket;

/**
 *
 * @author hasalp
 */
public class MQTTLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        Connection connection = new Connection();
        ServerSocket server = connection.createServer(1883);
        
        Reader reader = new Reader(server);
        reader.start();
    }
    
}
