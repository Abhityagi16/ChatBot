package in.example.docsapptask.chat;


import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.models.MessageType;
import in.example.docsapptask.data.source.ChatDao;
import in.example.docsapptask.data.source.DaoManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by abhishek.tyagi1 on 02/12/16.
 */

public class ChatListPresenter implements ChatActivityContract.Presenter {

    private static final long CHATBOT_ID = 63906;
    private ChatDao mChatDao;

    private ChatActivityContract.View mChatView;

    public ChatListPresenter(ChatActivityContract.View
            chatFragmentView) {
        mChatView = chatFragmentView;
        mChatDao = DaoManager.getChatDao();
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
    public void sendMessage(String message) {
        Message msg = new Message(CHATBOT_ID, message);
        msg.setMessageType(MessageType.SENT);
        mChatView.addMessage(msg);
        mChatView.clearMessage();
        mChatDao.sendMessage(msg)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BiConsumer<Message, Throwable>() {
                    @Override
                    public void accept(Message message, Throwable throwable) throws Exception {
                        if (throwable == null) {
                            message.setMessageType(MessageType.RECEIVED);
                            mChatView.addMessage(message);
                        }
                    }
                });

    }
}

