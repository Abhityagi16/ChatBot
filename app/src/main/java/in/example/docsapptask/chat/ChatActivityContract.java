package in.example.docsapptask.chat;


import in.example.docsapptask.BasePresenter;
import in.example.docsapptask.BaseView;
import in.example.docsapptask.data.models.Message;

/**
 * Created by abhishek.tyagi1 on 02/12/16.
 */

public interface ChatActivityContract {
    interface View extends BaseView<Presenter> {
        void addMessage(Message message);
    }

    interface Presenter extends BasePresenter {
        void sendMessage(String message);
    }
}
