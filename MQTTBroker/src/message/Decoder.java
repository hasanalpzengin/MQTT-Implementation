
package message;

public class Decoder {
    private static final int VARIABLE_SIZE_POS = 3;
    private static final int SUB_TOPIC_LENGTH_POS = 5;
    private static final int SIZE_POS = 1;
    private static final int IDENTIFIER_SIZE = 2;
    private static final int PACKAGE_IDENTIFIER_POS = 3;
    
    
    public static Message decode(byte[] input){
        Message decodedMsg = null;
        
        if(input[0] == 0x30){
            decodedMsg = publishDecode(input);
        }else if(input[0] == -0x10){
            decodedMsg = heartDecode(input);
        }else if(input[0] == 0x10){
            decodedMsg = connectDecode(input);
        }else if(input[0] == -0x8F+0x0F+0x02){
            decodedMsg = subscribeDecode(input);
        }
        return decodedMsg;
    }
    
    private static Message connectDecode(byte[] input){
        Message decodedMsg = new Message();
        decodedMsg.setType(1);
        int length = (int)input[SIZE_POS]; // remaining length
        decodedMsg.setSize(length);
        int protocolLength = (int)input[VARIABLE_SIZE_POS];
        decodedMsg.setVariable_size(protocolLength);
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<protocolLength; i++){
            sBuilder.append((char)input[i+IDENTIFIER_SIZE*2]);
        }
        decodedMsg.setVariable(sBuilder.toString());
        sBuilder.setLength(0);
        //message size loop
        for(int i=0; i<=length-(protocolLength+IDENTIFIER_SIZE); i++){
            sBuilder.append((char)input[i+IDENTIFIER_SIZE*2+protocolLength]);
        }
        decodedMsg.setPayload(sBuilder.toString());
        return decodedMsg;
    }
    
    private static Message publishDecode(byte[] input){
        Message decodedMsg = new Message();
        decodedMsg.setType(3);
        int length = (int)input[SIZE_POS]; // remaining length
        decodedMsg.setSize(length);
        int topicLength = (int)input[VARIABLE_SIZE_POS];
        decodedMsg.setVariable_size(topicLength);
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<topicLength; i++){
            sBuilder.append((char)input[i+IDENTIFIER_SIZE*2]);
        }
        decodedMsg.setVariable(sBuilder.toString());
        sBuilder.setLength(0);
        //message size loop
        for(int i=0; i<=length-(topicLength+IDENTIFIER_SIZE); i++){
            sBuilder.append((char)input[i+IDENTIFIER_SIZE*2+topicLength]);
        }
        decodedMsg.setPayload(sBuilder.toString());
        return decodedMsg;
    }

    private static Message heartDecode(byte[] input) {
        Message decodedMsg = new Message();
        decodedMsg.setType(15);
        int length = (int) input[SIZE_POS];
        decodedMsg.setPayload("<3");
        return decodedMsg;
    }

    private static Message subscribeDecode(byte[] input) {
        Message decodedMsg = new Message();
        decodedMsg.setType(8);
        int length = (int)input[SIZE_POS]; // remaining length
        decodedMsg.setSize(length);
        byte packageIdentifier = input[PACKAGE_IDENTIFIER_POS];
        int topicLength = (int)input[SUB_TOPIC_LENGTH_POS];
        //set package identifier as flag
        decodedMsg.setFlags(new byte[]{packageIdentifier});
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<topicLength; i++){
            sBuilder.append((char)input[i+IDENTIFIER_SIZE*2]);
        }
        decodedMsg.setVariable(sBuilder.toString());
        sBuilder.setLength(0);
        //qos
        byte qos = input[length+IDENTIFIER_SIZE-1];
        decodedMsg.setQos_level(qos);
        return decodedMsg;
    }
}
