
package message;

import java.util.HashMap;

public class Decoder {
    private static final int TOPIC_SIZE_POS = 3;
    private static final int SIZE_POS = 1;
    private static final int IDENTIFIER_SIZE = 2;
    private static final int PUB_TYPEQOS_POS = 0;
    
    public static Message decode(byte[] input){
        Message decodedMsg = null;
        int typeqos = (int)input[PUB_TYPEQOS_POS];
        int type = (int) Math.floor(typeqos/16);
        int qos = typeqos/2;
        if(type == 3){
            decodedMsg = publishDecode(input);
        }else if(type == 15){
            decodedMsg = heartDecode(input);
        }else{
            decodedMsg = new Message();
            decodedMsg.setType(-1);
        }
        return decodedMsg;
    }
    
    public static boolean isPubAck(byte[] data){
        if(data[0] == 0x40){
            return true;
        }
        return false;
    }
    
    private static Message publishDecode(byte[] input){
        Message decodedMsg = new Message();
        decodedMsg.setType(3);
        int qosLevel = input[PUB_TYPEQOS_POS];
        decodedMsg.setQos_level((byte) ((qosLevel%16)/2));
        int length = (int)input[SIZE_POS]; // remaining length
        decodedMsg.setSize(length);
        int topicLength = (int)input[TOPIC_SIZE_POS];
        decodedMsg.setTopic_size(topicLength);
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<topicLength; i++){
            sBuilder.append((char)input[i+IDENTIFIER_SIZE*2]);
        }
        decodedMsg.setTopic(sBuilder.toString());
        sBuilder.setLength(0);
        //is package identifier exists
        if(qosLevel!=0){
            decodedMsg.setIdentifier(new byte[]{input[IDENTIFIER_SIZE*2+topicLength], input[1+IDENTIFIER_SIZE*2+topicLength]});
            for(int i=0; i<=length-(topicLength+IDENTIFIER_SIZE*2); i++){
                sBuilder.append((char)input[i+IDENTIFIER_SIZE*3+topicLength]);
            }
        }else{
            //message size loop
            for(int i=0; i<=length-(topicLength+IDENTIFIER_SIZE); i++){
                sBuilder.append((char)input[i+IDENTIFIER_SIZE*2+topicLength]);
            }
        }
        decodedMsg.setMessage(sBuilder.toString());
        return decodedMsg;
    }

    private static Message heartDecode(byte[] input) {
        Message decodedMsg = new Message();
        decodedMsg.setType(15);
        int length = (int) input[SIZE_POS];
        decodedMsg.setMessage("<3");
        return decodedMsg;
    }

    public static boolean isPubrec(byte[] data) {
        if(data[0] == 0x50){
            return true;
        }
        return false;
    }
    
    public static boolean isPubcomp(byte[] data){
        if(data[0] == 0x70){
            return true;
        }
        return false;
    }
}
