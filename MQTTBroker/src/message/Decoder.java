
package message;

import java.util.HashMap;

public class Decoder {
    private static final int TOPIC_SIZE_POS = 3;
    private static final int SIZE_POS = 1;
    private static final int IDENTIFIER_SIZE = 2;
    public static Message decode(byte[] input){
        Message decodedMsg = null;
        if(input[0] == 0x30){
            decodedMsg = publishDecode(input);
        }else if(input[0] == -0x10){
            decodedMsg = heartDecode(input);
        }
        return decodedMsg;
    }
    
    private static Message publishDecode(byte[] input){
        Message decodedMsg = new Message();
        decodedMsg.setType(3);
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
        //message size loop
        for(int i=0; i<=length-(topicLength+IDENTIFIER_SIZE); i++){
            sBuilder.append((char)input[i+IDENTIFIER_SIZE*2+topicLength]);
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
}
