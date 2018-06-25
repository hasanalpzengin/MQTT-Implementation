package message;

import java.io.UnsupportedEncodingException;

public class MessageBuilder {
    
    public final byte[] connectMessage = {
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
    
    public final byte[] connackMessage = {
        0x20,
        0x02
        //flag
        //connect return code
    };
    
    public final byte[] publishMessage = {
        0x30, //Publish with QOS 0
        0x02, //Remaining length
        0x00, //MSB
        0x28 //LSB
        //topic
        //message
    };
    
    public final byte[] pubackMessage = {
        0x40,
        0x02
    };
    
    public final byte[] pubrecMessage = {
        0x50,
        0x02
    };
    
    public final byte[] pubrelMessage = {
        0x60,
        0x02
    };
    
    public final byte[] pubcompMessage = {
        0x70,
        0x02
    };
    
    public final byte[] subscribeMessage = {
        -0x8F+0x0F, //-80
        0x04, //length
        0x00, //Package Identifier MSB
        0x01, //Package Identifier LSB
        0x00,//topic MSB
        0x00//topic LSB
        //topic filter
        //qos
    };
    
    public final byte[] subackMessage = {
        -0x70,
        0x00, // length
        0x00, //MSB
        0x00 //LSB
        //topic
        //0 - qos0, 1 - qos1, 2 - qos2, 80 failure
    };
    
    public final byte[] unsubMessage = {
        -0x60,
        0x00, //remaining length
        0x00, //msb
        0x00, //lsb
        //topic
    };
    
    public final byte[] unsubackMessage = {
        -0x50,
        0x02
    };
    
    public final byte[] pingreqMessage = {
        -0x40,
        0x00
    };
    
    public final byte[] pingrespMessage = {
        -0x30,
        0x00
    };
    
    public final byte[] disconnectMessage = {-0x7F+0x5F, 0x00};

    public final int LENGTH_POS = 1;
    public final int CON_STR_LENGTH_POS = 13;
    public final int PUB_TOPIC_LENGTH_POS = 3;
    public final int SUB_TOPIC_LENGTH_POS = 5;
    public final int SUB_IDENTIFIER_SIZE = 4; // for lsb and msb size
    public final int SUB_TOPIC_IDENTIFIER_SIZE = 2;
    private static byte identifier = 1;
    
    public byte[] buildConnect(String sid) throws UnsupportedEncodingException{
        byte[] id = Encoder.encode(sid);
        byte[] mergedMessage = new byte[id.length+connectMessage.length];
        System.arraycopy(connectMessage, 0, mergedMessage, 0, connectMessage.length);
        System.arraycopy(id, 0, mergedMessage, connectMessage.length, id.length);
        
        byte encodedLength = (byte)id.length;
        //length set
        mergedMessage[LENGTH_POS] += encodedLength;
        mergedMessage[CON_STR_LENGTH_POS] = encodedLength;
        
        return mergedMessage;
    }
    
    public byte[] buildPublish(String sMessage, String sTopic) throws UnsupportedEncodingException{
        byte[] message = Encoder.encode(sMessage);
        byte[] topic = Encoder.encode(sTopic);
        byte[] mergedMessage = new byte[message.length+publishMessage.length+topic.length];
        System.arraycopy(publishMessage, 0, mergedMessage, 0, publishMessage.length);
        System.arraycopy(topic, 0, mergedMessage, publishMessage.length, topic.length);
        System.arraycopy(message, 0, mergedMessage, publishMessage.length+topic.length, message.length);
        
        byte encodedMessageLength = (byte)message.length;
        byte encodedTopicLength = (byte)topic.length;
        //length set
        mergedMessage[LENGTH_POS] += encodedMessageLength + encodedTopicLength;
        mergedMessage[PUB_TOPIC_LENGTH_POS] = encodedTopicLength;
        return mergedMessage;
    }

    public byte[] buildSubscribe(int qosLevel, String stopic) throws UnsupportedEncodingException {
        byte[] topic = Encoder.encode(stopic);
        byte bqos = (byte) qosLevel;
        byte[] qos = new byte[1];
        qos[0] = bqos;
        System.out.println(bqos);
        byte[] mergedMessage = new byte[subscribeMessage.length+topic.length+qos.length];
        
        System.arraycopy(subscribeMessage, 0, mergedMessage, 0, subscribeMessage.length);
        System.arraycopy(topic, 0, mergedMessage,subscribeMessage.length, topic.length);
        System.arraycopy(qos, 0, mergedMessage, subscribeMessage.length+topic.length, qos.length);
        
        byte encodedTopicLength = (byte) topic.length;
        byte encodedQosLength = (byte) qos.length;
        
        mergedMessage[LENGTH_POS] += encodedTopicLength + encodedQosLength;
        mergedMessage[PUB_TOPIC_LENGTH_POS] = identifier;
        mergedMessage[SUB_TOPIC_LENGTH_POS] = encodedTopicLength;
        
        identifier++;
        
        System.out.print(javax.xml.bind.DatatypeConverter.printHexBinary(mergedMessage));
        return mergedMessage;
    }
}