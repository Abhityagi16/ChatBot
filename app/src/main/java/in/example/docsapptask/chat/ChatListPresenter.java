package in.example.docsapptask.chat;


import in.example.docsapptask.data.source.ChatDao;

/**
 * Created by abhishek.tyagi1 on 02/12/16.
 */

public class ChatListPresenter implements ChatActivityContract.Presenter {

    private ChatDao mChatDao;

    ChatActivityContract.View mChatView;



    public ChatListPresenter(ChatActivityContract.View
            chatFragmentView) {
        mChatView = chatFragmentView;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }


}

