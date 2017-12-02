package in.example.docsapptask.data.models;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class Message {
    private String chatBotName, message;
    private long chatBotID;

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
