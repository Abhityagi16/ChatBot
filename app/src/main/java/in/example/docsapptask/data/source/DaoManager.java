package in.example.docsapptask.data.source;

import in.example.docsapptask.data.source.local.LocalChatDataSource;
import in.example.docsapptask.data.source.remote.RemoteChatDataSource;

/**
 * Created by abhishektyagi on 02/12/17.
 */

// This class is used to manage multiple Database entities
public class DaoManager {

    public static ChatDao getChatDao() {
        return ChatDao.getInstance(RemoteChatDataSource.getInstance(), LocalChatDataSource
                .getInstance());
    }
}
