package com.mycompany.raspberrypihumidity;
import connection.Connection;
import connection.Publish;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author hasalp
 */
public class RaspberryPiHumidity{


    public static void main(String[] args) {
        ReaderThread reader = new ReaderThread();
        reader.start();
    }
    
    
    
}
