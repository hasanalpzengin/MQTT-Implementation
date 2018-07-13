/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.raspberrypihumidity;

import connection.AutoPublish;
import connection.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hasalp
 */
public class ReaderThread extends Thread {

    @Override
    public void run() {
        try {
            Connection.connect("192.168.1.1", "Hasan");
            //Heart heartPublisher = new Heart();
            //byte puback[] = heartPublisher.heart();
            //if(Decoder.isPubAck(puback)){
            //System.out.println("Heart Sent");
            //}
            if(Connection.isConnected()){
                HumidityReader reader = new HumidityReader();
                AutoPublish publish = new AutoPublish();
                publish.setTopic("test");
                publish.setQos(2);
                publish.autoPublish();
                publish.setMessage("");
                while(true){
                    int[] results=reader.read();
                    publish.setMessage(String.valueOf(results[0]));
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RaspberryPiHumidity.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RaspberryPiHumidity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
