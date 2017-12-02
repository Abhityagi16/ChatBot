package in.example.docsapptask.chat;


import java.util.List;

import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.models.MessageType;
import in.example.docsapptask.data.source.ChatDao;
import in.example.docsapptask.data.source.DaoManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by abhishek.tyagi1 on 02/12/16.
 */

public class ChatListPresenter implements ChatActivityContract.Presenter {

    private static final long CHATBOT_ID = 63906;
    private ChatDao mChatDao;

    private ChatActivityContract.View mChatView;

    public ChatListPresenter(ChatActivityContract.View
            chatFragmentView, ChatDao chatDao) {
        mChatView = chatFragmentView;
        mChatDao = chatDao;
    }

    //Since we are using just Single(from RxJava) in this app we won't use these methods as Single
    // dispose by itself on completion
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loadSavedMessages() {
        mChatDao.loadMessages()
                .subscribeOn(Schedulers.io())                   // Db operation on background thread
                .observeOn(AndroidSchedulers.mainThread())      // Observing on Main thread
                .subscribe(new Consumer<List<Message>>() {
                    @Override
                    public void accept(List<Message> messages) throws Exception {
                        mChatView.setMessages(messages);
                    }
                });
    }

    @Override
    public void sendMessage(String message) {
        Message msg = new Message(CHATBOT_ID, message);
        msg.setMessageType(MessageType.SENT);
        msg.setTimestamp(System.currentTimeMillis());
        mChatView.addMessage(msg);
        mChatView.clearMessage();
        mChatDao.sendMessage(msg)
                .subscribeOn(Schedulers.io())                   // Remote call on background thread
                .observeOn(AndroidSchedulers.mainThread())      // Observe on main thread
                .subscribe(new BiConsumer<Message, Throwable>() {
                    @Override
                    public void accept(Message message, Throwable throwable) throws Exception {
                        if (throwable == null) {
                            mChatView.addMessage(message);
                        } else {
                            mChatView.showErrorToast(throwable.getMessage());
                        }
                    }
                });

    }

    @Override
    public void releaseResources() {
        mChatDao.closeDatabase();
    }
}

