
package message;

public class Decoder {
    /**
     * MOST USING BYTE POSSITIONING ON MQTT
     * TYPE | MSG_SIZE | VAR_SIZE_MSB | VAR_SIZE_LSB | VARIABLE | .. | PAYLOAD_SIZE | PAYLOAD | ...
    **/
    private static final int VARIABLE_SIZE_POS = 3;
    private static final int SUB_TOPIC_LENGTH_POS = 5;
    private static final int SIZE_POS = 1;
    private static final int IDENTIFIER_SIZE = 2;
    private static final int PACKAGE_IDENTIFIER_POS = 3;
    private static final int PUB_TYPEQOS_POS = 0;
    
    /*
        Decode function gets byte data and parses first byte to defining type of message
        after defination associated function starts for keep decoding process
    */
    public static Message decode(byte[] input){
        Message decodedMsg = null;
        int typeqos = (int)input[PUB_TYPEQOS_POS];
        //first digit is for type
        int type = (int) Math.floor(typeqos/16);
        //second digit is for qos
        // 82 means subsribe with qos 1
        int qos = typeqos/2;
        switch(type){
            case 1: decodedMsg = connectDecode(input); break;
            case 3: decodedMsg = publishDecode(input); break;
            //-7 is equals to 8
            case -7: decodedMsg = subscribeDecode(input); break;
            case 15: decodedMsg = heartDecode(input); break;
            default: decodedMsg = new Message(); decodedMsg.setType(-1); break;
        }
        return decodedMsg;
    }
    
    private static Message connectDecode(byte[] input){
        Message decodedMsg = new Message();
        // type 1 is reserved for connect
        decodedMsg.setType(1);
        // get msg length to parse
        int length = (int)input[SIZE_POS]; // remaining length
        decodedMsg.setSize(length);
        // get variable length
        // variable means protocol name in connect message
        int protocolLength = (int)input[VARIABLE_SIZE_POS];
        decodedMsg.setVariable_size(protocolLength);
        //create string builder to store bytes of protocol name in one string
        StringBuilder sBuilder = new StringBuilder();
        //topic size loop
        for(int i=0; i<protocolLength; i++){
            // before protocol byte we have 4 byte with represents type, msg size, protocol size msb, protocol size lsb
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
        //msg type 3 is reserved to publish message in mqtt
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
        // don't have to do but it's cute
        // decodedMsg.setPayload("<3");
        return decodedMsg;
    }

    private static Message subscribeDecode(byte[] input) {
        Message decodedMsg = new Message();
        //msg type 8 is reserved for subscribe
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
        //0x60 is reserved for pubrel
        return data[0] == 0x60;
    }
}
