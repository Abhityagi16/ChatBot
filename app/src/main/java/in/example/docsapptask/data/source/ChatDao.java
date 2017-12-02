package in.example.docsapptask.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.source.local.LocalChatDataSource;
import in.example.docsapptask.data.source.remote.RemoteChatDataSource;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class ChatDao implements ChatDataSource {

    @Nullable
    private static ChatDao INSTANCE = null;
    private final RemoteChatDataSource mChatRemoteDataSource;

    private final LocalChatDataSource  mChatLocalDataSource;        //Since we are not storing

    private ChatDao(@NonNull RemoteChatDataSource chatRemoteDataSource,
                       @NonNull LocalChatDataSource chatLocalDataSource) {
        mChatRemoteDataSource = checkNotNull(chatRemoteDataSource);

        mChatLocalDataSource = checkNotNull(chatLocalDataSource);
    }

    public static ChatDao getInstance(@NonNull RemoteChatDataSource chatRemoteDataSource,
                                         @NonNull LocalChatDataSource chatLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ChatDao(chatRemoteDataSource, chatLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Single<Message> sendMessage(Message message) {
        mChatLocalDataSource.saveMessage(message);
        return mChatRemoteDataSource.sendMessage(message)
                .doOnSuccess(new Consumer<Message>() {
                    @Override
                    public void accept(Message message) throws Exception {
                        mChatLocalDataSource.saveMessage(message);
                    }
                });
    }

    @Override
    public Single<List<Message>> loadMessages() {
        return mChatLocalDataSource.loadMessages();
    }

    @Override
    public void closeDatabase() {
        mChatLocalDataSource.closeDatabase();
    }
}
