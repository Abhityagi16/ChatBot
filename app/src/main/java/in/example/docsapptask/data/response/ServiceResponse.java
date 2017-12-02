package in.example.docsapptask.data.response;


import in.example.docsapptask.data.models.Message;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class ServiceResponse {
    private int success;
    private String errorMessage;
    private Message message;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
