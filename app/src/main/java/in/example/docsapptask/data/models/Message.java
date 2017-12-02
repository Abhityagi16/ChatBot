package in.example.docsapptask.data.models;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class Message {
    private String chatBotName, message;
    private long chatBotID;
    private MessageType messageType;
    private long timestamp;

    public Message(long chatBotID, String message) {
        this.message = message;
        this.chatBotID = chatBotID;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getChatBotName() {
        return chatBotName;
    }

    public void setChatBotName(String chatBotName) {
        this.chatBotName = chatBotName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getChatBotID() {
        return chatBotID;
    }

    public void setChatBotID(long chatBotID) {
        this.chatBotID = chatBotID;
    }
}
