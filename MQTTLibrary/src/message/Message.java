package message;

public class Message {
    private int type;
    private int size;
    private int topic_size;
    private int message_size;
    private String message;
    private String topic;

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
