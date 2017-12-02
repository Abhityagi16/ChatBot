package in.example.docsapptask.data.source;

import java.util.List;

import in.example.docsapptask.data.models.Message;
import io.reactivex.Single;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public interface ChatDataSource {

    Single<Message> sendMessage(Message message);
    Single<List<Message>> loadMessages();

}
