package in.example.docsapptask.data.models;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public enum MessageType {
    SENT(1), RECEIVED(2);

    private int id;

    MessageType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
