package Data;

import java.io.Serializable;

public class Message implements Serializable {
    private String sender;
    private String receiver;
    private String details;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Message() {
        this.details = "";
        this.receiver = "";
        this.sender = "";
    }

    public Message(String sender, String receiver, String details) {
        this.sender = sender;
        this.receiver = receiver;
        this.details = details;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
