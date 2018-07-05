package message;

public class Message {
    private int type = -1;
    private int size;
    private int variable_size;
    private int payload_size;
    private byte qos_level;
    private String payload;
    private String variable;
    private byte[] flags;

    public byte[] getFlags() {
        return flags;
    }

    public void setFlags(byte[] flags) {
        this.flags = flags;
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

    public int getVariable_size() {
        return variable_size;
    }

    public void setVariable_size(int variable_size) {
        this.variable_size = variable_size;
    }

    public int getPayload_size() {
        return payload_size;
    }

    public void setPayload_size(int payload_size) {
        this.payload_size = payload_size;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public byte getQos_level() {
        return qos_level;
    }

    public void setQos_level(byte qos_level) {
        this.qos_level = qos_level;
    }
    
    
    
}
