/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mqttlibrary;

import java.io.UnsupportedEncodingException;
import message.Encoder;
import connection.Connection;
import connection.Publish;
import connection.Subscribe;

/**
 *
 * @author hasalp
 */
public class MQTTLibrary {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] connack = Connection.connect("::1", "Hasan");
        //Publish publisher = new Publish();
        Subscribe subscriber = new Subscribe();
        if(connack[0] == 0x20 && connack[2] == 0x00) {
            Connection.connected = true;
            //publisher.setTopic("house/room1/temperature");
            //publisher.setMessage("22");
            //publisher.publish();
            subscriber.setTopic("house/room1/temperature");
            subscriber.setQos(0);
            subscriber.subscribe();
        }else{
            System.out.println("Connection Refused");
        }
    }
    
}
