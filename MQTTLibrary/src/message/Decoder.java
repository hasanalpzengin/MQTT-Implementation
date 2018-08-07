
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
        decodedMsg.setTopic_size(topicLength);
        //fourth byte parsed
        counter++;
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<topicLength; i++){
            sBuilder.append((char)input[i+counter]);
        }
        decodedMsg.setTopic(sBuilder.toString());
        //topic bytes parsed
        counter += topicLength;
        sBuilder.setLength(0);
        //is package identifier exists
        if(decodedMsg.getQos_level()!=0){
            decodedMsg.setIdentifier(new byte[]{input[counter], input[1+counter]});
            //identifier parsed
            counter += IDENTIFIER_SIZE;
            System.out.println(counter);
            for(int i=counter; i<input.length; i++){
                sBuilder.append((char)input[i]);
            }
        }else{
            //message size loop
            for(int i=counter; i<input.length; i++){
                sBuilder.append((char)input[i]);
            }
        }
        decodedMsg.setMessage(sBuilder.toString());
        System.out.println("Pub Message Fetched: "+decodedMsg.getTopic()+" : "+decodedMsg.getMessage());
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
