package in.example.docsapptask.chat;


import in.example.docsapptask.BasePresenter;
import in.example.docsapptask.BaseView;

/**
 * Created by abhishek.tyagi1 on 02/12/16.
 */

public interface ChatActivityContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
    }
}
