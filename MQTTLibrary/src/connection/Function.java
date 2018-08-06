package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import message.MessageBuilder;

/**
 *
 * @author hasalp
 */
public class Function {
    OutputStream outStream;
    DataOutputStream doutStream;
    InputStream inStream;
    DataInputStream dinStream;
    MessageBuilder builder;
    Socket socket;

    public Function(Socket socket) {
        this.socket = socket;
        initIO();
    }
    
    public void initIO(){
        try {
            outStream = socket.getOutputStream();
            doutStream = new DataOutputStream(outStream);
            inStream = socket.getInputStream();
            dinStream = new DataInputStream(inStream);
            builder = new MessageBuilder();
        } catch (IOException ex) {
            Logger.getLogger(Function.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("IOException Error");
        }
    }
}
