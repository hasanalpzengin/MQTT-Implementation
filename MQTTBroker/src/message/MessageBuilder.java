package message;

import java.io.UnsupportedEncodingException;

public class MessageBuilder {
    
    private final byte[] connackMessage = {
        0x20,//type
        0x02,//size
        0x00,//flag
        0x00//status
    };
    
    private final byte[] pubackMessage = {
        0x40,//type
        0x02//flag(success)
    };
    
    private final byte[] pubrecMessage = {
        0x50,//type
        0x02//flag(success)
    };
    
    private final byte[] pubcompMessage = {
        0x70,//type
        0x02//flag(success)
    };
   
    
    private final byte[] subackMessage = {
        -0x70,
        0x04, // length
        0x00, //MID MSB
        0x01, //MID LSB
        0x00,//identifier
        0x00//0 - qos0, 1 - qos1, 2 - qos2, 80 failure
    };
    
    private final byte[] unsubMessage = {
        -0x60,//type
        0x00, //remaining length
        0x00, //msb
        0x00, //lsb
        //topic
    };
    
    private final byte[] unsubackMessage = {
        -0x50,//type
        0x02//flag(success)
    };
    
    private final byte[] pingreqMessage = {
        -0x40,//type
        0x02//flag(success)
    };
    
    private final byte[] pingrespMessage = {
        -0x30,//type
        0x02//flag(success)
    };
        
    private final byte[] heartMessage = {
        -0x10, //F
        0x00//flag(don't care)
    };
    //  Subscribe acknowledge message's QOS byte's position
    private final int SUBACK_QOS_POS = 5;
    //  Subscribe acknowledge message's identifier byte's position
    private final int SUBACK_IDENTIFIER_POS = 4;
    //  connection acknowledge message's status byte's position
    private final int CONNACK_STATUS_POS = 3;
    
    public byte[] buildConnack(boolean success) throws UnsupportedEncodingException{
        byte[] mergedMessage = new byte[connackMessage.length];
        System.arraycopy(connackMessage, 0, mergedMessage, 0, connackMessage.length);
        if(success){
            mergedMessage[CONNACK_STATUS_POS] = 0x00;//connected
        }else{
            mergedMessage[CONNACK_STATUS_POS] = 0x02;//connection refused
        }
        
        return mergedMessage;
    }

    public byte[] buildSuback(byte qosLevel, byte identifier) throws UnsupportedEncodingException {
        byte bqos = (byte) qosLevel;
        byte[] qos = new byte[1];
        qos[0] = bqos;
        System.out.println(bqos);
        byte[] mergedMessage = subackMessage;
        
        mergedMessage[SUBACK_IDENTIFIER_POS] = identifier;
        mergedMessage[SUBACK_QOS_POS] = qosLevel;
        //byte print
        //System.out.print(javax.xml.bind.DatatypeConverter.printHexBinary(mergedMessage));
        return mergedMessage;
    }
    
    public byte[] buildHeart(){
        return heartMessage;
    }

    public byte[] buildPuback(){
        return pubackMessage;
    }
    
    public byte[] buildPubrec(){
        return pubrecMessage;
    }
    
    public byte[] buildPubcomp(){
        return pubcompMessage;
    }
}
