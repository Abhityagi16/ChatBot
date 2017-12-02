package in.example.docsapptask.data.source.local;

import java.util.List;

import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.source.ChatDataSource;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by abhishektyagi on 02/12/17.
 */


public class LocalChatDataSource {

    private static LocalChatDataSource INSTANCE;

    private LocalChatDataSource() {

    }

    public static LocalChatDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalChatDataSource();
        }

        return INSTANCE;

    }

    public Single<List<Message>> loadMessages() {
        return null;
    }

    public void saveMessage(Message message) {
    }
}
