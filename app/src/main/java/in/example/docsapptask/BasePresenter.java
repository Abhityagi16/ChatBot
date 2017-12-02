package in.example.docsapptask;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public interface BasePresenter {

    //Since we are using just Single in this app we won't use these methods as Single dispose by
    // itself on completion
    void subscribe();
    void unsubscribe();
}
