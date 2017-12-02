package in.example.docsapptask.data.source.local;

import android.provider.BaseColumns;

/**
 * The contract used for the db to save the tasks locally.
 */
public final class ChatPersistenceContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ChatPersistenceContract() {}

    /* Inner class that defines the table contents */
    public static abstract class ChatEntry implements BaseColumns {
        public static final String TABLE_NAME = "chats";
        public static final String COLUMN_NAME_MESSAGE_TYPE = "message_type";
        public static final String COLUMN_NAME_MESSAGE = "message";
        public static final String COLUMN_NAME_TIMESTAMP = "timestamp";

    }
}
