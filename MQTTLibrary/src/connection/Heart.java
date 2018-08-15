package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.Decoder;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 * 
 * This class extends Function class and implements Runnable
 * Function class gains privileges to publish or recieve message
 * Runnable used to run function as Thread
 */
public class Heart extends Function implements Runnable {
    
    private byte[] heartMessage;
    
    public Heart(Connection connection){
        //super(socket) for init IO class
        super(connection.socket);
    }

    @Override
    public void run() {
        try {
            //create heart message = F type
            heartMessage = builder.buildHeart();
            //sent heart message
            doutStream.write(heartMessage);
            doutStream.flush();
            Thread.sleep(100);
            //recieve puback
            byte[] puback = new byte[2];
            dinStream.read(puback);
            if(Decoder.isPubAck(puback)){
                System.out.println("Sending Success");
            }else{
                System.out.println("Sending Failed");
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Publish.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
    }
    
    
}
