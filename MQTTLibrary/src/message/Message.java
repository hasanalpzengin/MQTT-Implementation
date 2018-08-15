package message;

public class Message {
    private int type;
    private int size;
    private int topic_size;
    private int message_size;
    private int qos_level;
    private byte[] identifier;
    private String message;
    private String topic;
    
    /**
     * 
     *  MSG_TYPE | MSG_LENGTH | VARIABLE_SIZE | VARIBLE_1 | ... | VARIABLE_N | IDENTIFIER | PAYLOAD_1 | ... | PAYLOAD_N
     * 
     * at client;
     * variable means topic
     * payload means message
     */

    public byte[] getIdentifier() {
        return identifier;
    }

    public void setIdentifier(byte[] identifier) {
        this.identifier = identifier;
    }
    
    public int getQos_level() {
        return qos_level;
    }

    public void setQos_level(int qos_level) {
        this.qos_level = qos_level;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTopic_size() {
        return topic_size;
    }

    public void setTopic_size(int topic_size) {
        this.topic_size = topic_size;
    }

    public int getMessage_size() {
        return message_size;
    }

    public void setMessage_size(int message_size) {
        this.message_size = message_size;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
