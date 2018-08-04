package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 */
public abstract class Function {
    OutputStream outStream;
    DataOutputStream doutStream;
    InputStream inStream;
    DataInputStream dinStream;
    MessageBuilder builder;

    public Function() {
        initIO();
    }
    
    public void initIO(){
        try {
            outStream = Connection.socket.getOutputStream();
            doutStream = new DataOutputStream(outStream);
            inStream = Connection.socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            builder = new MessageBuilder();
        } catch (IOException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IOException Error");
        }
    }
}
