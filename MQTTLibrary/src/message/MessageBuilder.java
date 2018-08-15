package message;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class MessageBuilder {
    
    private final byte[] connectMessage = {
        0x10, //Connect
        0x0C, //Remaining Length
        0x00, //Protocol Name MSB 0
        0x04, //Protocol Name LSB 4
        0x4d, //M
        0x51, //Q
        0x54, //T
        0x54, //T
        0x04, //Protocol version = 3
        0x02, //Clean session only
        0x00, //Keepalive MSB
        0x3c, //Keepalive LSB = 60 
        0x00, //id length MSB
        0x14 //id length LSB = 20
        //id
    };
    
    private final byte[] connackMessage = {
        0x20,
        0x02
        //flag
        //connect return code
    };
    
    private final byte[] publishMessage = {
        0x30, //Publish with QOS 0
        0x02, //Remaining length
        0x00, //MSB
        0x28 //LSB
        //topic
        //message
    };
    
    private final byte[] pubackMessage = {
        0x40,
        0x02
    };
    
    private final byte[] pubrecMessage = {
        0x50,
        0x02
    };
    
    private final byte[] pubrelMessage = {
        0x60,
        0x02
    };
    
    private final byte[] pubcompMessage = {
        0x70,
        0x02
    };
    
    private final byte[] subscribeMessage = {
        -0x8F+0x0F+0x02, //-82
        0x04, //length
        0x00, //Package Identifier MSB
        0x01, //Package Identifier LSB
        0x00,//topic MSB
        0x00//topic LSB
        //topic filter
        //qos
    };
    
    private final byte[] subackMessage = {
        -0x70,
        0x00, // length
        0x00, //MSB
        0x00 //LSB
        //topic
        //0 - qos0, 1 - qos1, 2 - qos2, 80 failure
    };
    
    private final byte[] unsubMessage = {
        -0x60,
        0x00, //remaining length
        0x00, //msb
        0x00, //lsb
        //topic
    };
    
    private final byte[] unsubackMessage = {
        -0x50,
        0x02
    };
    
    private final byte[] pingreqMessage = {
        -0x40,
        0x00
    };
    
    private final byte[] pingrespMessage = {
        -0x30,
        0x00
    };
    
    private final byte[] disconnectMessage = {-0x20, 0x00};
    
    private final byte[] heartMessage = {
        -0x10, //F
        0x00
    };
    //final variables
    //generic possition for all messages
    public final int LENGTH_POS = 1;
    //connection message length byte's position
    public final int CON_LENGTH_POS = 13;
    //publish message topic length byte's position
    public final int PUB_TOPIC_LENGTH_POS = 3;
    //subscribe message length byte's position
    public final int SUB_TOPIC_LENGTH_POS = 5;
    //subcribe message identifier byte's pos
    public final int SUB_IDENTIFIER_POS = 3;
    //subscribe message topic identifier byte's pos
    public final int SUB_TOPIC_IDENTIFIER_SIZE = 2;
    //publish message's qos byte's pos
    public final int PUB_QOS_POS = 0;
    //this identifier will change its value after each message creation
    private static byte identifier = 1;
    
    public byte[] buildConnect(String sid) throws UnsupportedEncodingException{
        //encode string id to byte id
        byte[] id = Encoder.encode(sid);
        //set a byte array which has enough size to fit variables as final msg
        //ID + connectMSG
        byte[] mergedMessage = new byte[id.length+connectMessage.length];
        //first put connect msg to final byte array
        System.arraycopy(connectMessage, 0, mergedMessage, 0, connectMessage.length);
        //put id
        System.arraycopy(id, 0, mergedMessage, connectMessage.length, id.length);
        //get size of id to set length of message
        byte encodedLength = (byte)id.length;
        //length set
        mergedMessage[LENGTH_POS] += encodedLength;
        mergedMessage[CON_LENGTH_POS] = encodedLength;
        
        return mergedMessage;
    }
    
    public byte[] buildPublish(String sMessage, String sTopic, int qos) throws UnsupportedEncodingException{
        Random random = new Random();
        byte[] message = Encoder.encode(sMessage);
        byte[] topic = Encoder.encode(sTopic);
        byte encodedMessageLength;
        byte[] mergedMessage;
        if(qos != 0){
            //package identifier
            byte[] packageIdentifier = new byte[2];
            random.nextBytes(packageIdentifier);
            //package build
            mergedMessage = new byte[message.length+publishMessage.length+topic.length+packageIdentifier.length];
            System.arraycopy(publishMessage, 0, mergedMessage, 0, publishMessage.length);
            System.arraycopy(topic, 0, mergedMessage, publishMessage.length, topic.length);
            System.arraycopy(packageIdentifier, 0, mergedMessage, publishMessage.length+topic.length, packageIdentifier.length);
            System.arraycopy(message, 0, mergedMessage, publishMessage.length+topic.length+packageIdentifier.length, message.length);
            encodedMessageLength = (byte) (message.length + packageIdentifier.length);
        }else{
            mergedMessage = new byte[message.length+publishMessage.length+topic.length];
            System.arraycopy(publishMessage, 0, mergedMessage, 0, publishMessage.length);
            System.arraycopy(topic, 0, mergedMessage, publishMessage.length, topic.length);
            System.arraycopy(message, 0, mergedMessage, publishMessage.length+topic.length, message.length);
            encodedMessageLength = (byte)message.length;
        }
        
        byte encodedTopicLength = (byte)topic.length;
        //length set
        mergedMessage[LENGTH_POS] += encodedMessageLength + encodedTopicLength;
        mergedMessage[PUB_QOS_POS] += (qos*2);
        mergedMessage[PUB_TOPIC_LENGTH_POS] = encodedTopicLength;
        return mergedMessage;
    }

    public byte[] buildSubscribe(int qosLevel, String stopic) throws UnsupportedEncodingException {
        byte[] topic = Encoder.encode(stopic);
        byte bqos = (byte) qosLevel;
        byte[] qos = new byte[1];
        qos[0] = bqos;
        byte[] mergedMessage = new byte[subscribeMessage.length+topic.length+qos.length];
        
        System.arraycopy(subscribeMessage, 0, mergedMessage, 0, subscribeMessage.length);
        System.arraycopy(topic, 0, mergedMessage,subscribeMessage.length, topic.length);
        System.arraycopy(qos, 0, mergedMessage, subscribeMessage.length+topic.length, qos.length);
        
        byte encodedTopicLength = (byte) topic.length;
        byte encodedQosLength = (byte) qos.length;
        
        mergedMessage[LENGTH_POS] += encodedTopicLength + encodedQosLength;
        mergedMessage[SUB_IDENTIFIER_POS] = identifier;
        mergedMessage[SUB_TOPIC_LENGTH_POS] = encodedTopicLength;
        
        identifier++;
        //print byte output
        //System.out.print(javax.xml.bind.DatatypeConverter.printHexBinary(topic));
        System.out.println("");
        for(int i=0; i<topic.length; i++){
            System.out.print((char)topic[i]);
        }
        
        return mergedMessage;
    }
    
    public byte[] buildHeart(){
        return heartMessage;
    }
    
    public byte[] buildPubrel(){
        return pubrelMessage;
    }
    
    public byte[] buildDisconnect(){
        return disconnectMessage;
    }
    
}
