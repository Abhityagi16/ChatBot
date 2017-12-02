package in.example.docsapptask.data.source.local;

import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.source.ChatDataSource;
import io.reactivex.Single;

/**
 * Created by abhishektyagi on 02/12/17.
 */


public class LocalChatDataSource implements ChatDataSource {

    private static LocalChatDataSource INSTANCE;

    private LocalChatDataSource() {

    }

    public static LocalChatDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new LocalChatDataSource();
        }

        return INSTANCE;

    }

    @Override
    public Single<Message> getResponse(Message message) {
        return null;
    }
}
