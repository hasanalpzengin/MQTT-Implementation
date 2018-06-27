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

/**
 *
 * @author hasalp
 */
public class MQTTLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        Connection.createServer(1883);
        Reader reader = new Reader();
        reader.start();
    }
    
}
