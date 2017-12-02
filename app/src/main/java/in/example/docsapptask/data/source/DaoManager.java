package in.example.docsapptask.data.source;

import in.example.docsapptask.data.source.local.LocalChatDataSource;
import in.example.docsapptask.data.source.remote.RemoteChatDataSource;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class DaoManager {

    public static ChatDao getFlightsDao() {
        return ChatDao.getInstance(RemoteChatDataSource.getInstance(), LocalChatDataSource
                .getInstance());
    }
}
