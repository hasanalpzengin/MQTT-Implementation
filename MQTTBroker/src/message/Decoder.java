
package message;

public class Decoder {
    private static final int VARIABLE_SIZE_POS = 3;
    private static final int SUB_TOPIC_LENGTH_POS = 5;
    private static final int SIZE_POS = 1;
    private static final int IDENTIFIER_SIZE = 2;
    private static final int PACKAGE_IDENTIFIER_POS = 3;
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
        }else if(type == 1){
            decodedMsg = connectDecode(input);
        }else if(type == -7){
            decodedMsg = subscribeDecode(input);
        }else{
            decodedMsg = new Message();
            decodedMsg.setType(-1);
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
        int counter = 0;
        Message decodedMsg = new Message();
        decodedMsg.setType(3);
        //first byte type+qos
        int qosLevel = input[counter];
        decodedMsg.setQos_level((byte) ((qosLevel%16)/2));
        //first byte parsed
        counter++;
        int length = (int)input[counter]; // remaining length
        decodedMsg.setSize(length);
        //second byte parsed
        counter++; // passing msb
        counter++; // lsb of topic length
        int topicLength = (int)input[counter];
        decodedMsg.setVariable_size(topicLength);
        //fourth byte parsed
        counter++;
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<topicLength; i++){
            sBuilder.append((char)input[i+counter]);
        }
        decodedMsg.setVariable(sBuilder.toString());
        //topic bytes parsed
        counter += topicLength;
        sBuilder.setLength(0);
        //is package identifier exists
        if(decodedMsg.getQos_level()!=0){
            decodedMsg.setIdentifier(new byte[]{input[counter], input[1+counter]});
            //identifier parsed
            counter += IDENTIFIER_SIZE;
            for(int i=counter; i<input.length; i++){
                sBuilder.append((char)input[i]);
            }
        }else{
            //message size loop
            for(int i=counter; i<input.length; i++){
                sBuilder.append((char)input[i]);
            }
        }
        decodedMsg.setPayload(sBuilder.toString());
        
        System.out.println("Pub Message Fetched: "+decodedMsg.getVariable()+" : "+decodedMsg.getPayload());
        
        return decodedMsg;
    }

    private static Message heartDecode(byte[] input) {
        Message decodedMsg = new Message();
        decodedMsg.setType(15);
        int length = (int) input[SIZE_POS];
        //decodedMsg.setPayload("<3");
        return decodedMsg;
    }

    private static Message subscribeDecode(byte[] input) {
        Message decodedMsg = new Message();
        decodedMsg.setType(8);
        //print byte
        //System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(input));
        int length = (int)input[SIZE_POS]; // remaining length
        decodedMsg.setSize(length);
        byte packageIdentifier = input[PACKAGE_IDENTIFIER_POS];
        int topicLength = (int)input[SUB_TOPIC_LENGTH_POS];
        //set package identifier as flag
        decodedMsg.setFlags(new byte[]{packageIdentifier});
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<topicLength; i++){
            sBuilder.append((char)input[i+(IDENTIFIER_SIZE*3)]);
        }
        decodedMsg.setVariable(sBuilder.toString());
        sBuilder.setLength(0);
        //qos
        byte qos = input[length+IDENTIFIER_SIZE-1];
        decodedMsg.setQos_level(qos);
        System.out.println("Sub Message Fetched: "+decodedMsg.getVariable()+" : "+decodedMsg.getPayload());
        return decodedMsg;
    }
    
    public static boolean isPubrel(byte[] data){
        if(data[0] == 0x60){
            return true;
        }
        return false;
    }
}
