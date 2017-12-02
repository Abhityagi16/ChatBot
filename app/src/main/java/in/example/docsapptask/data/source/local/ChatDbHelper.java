package in.example.docsapptask.data.source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChatDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Chats.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ChatPersistenceContract.ChatEntry.TABLE_NAME + " (" +
                    ChatPersistenceContract.ChatEntry.COLUMN_NAME_MESSAGE_TYPE + INTEGER_TYPE + COMMA_SEP +
                    ChatPersistenceContract.ChatEntry.COLUMN_NAME_MESSAGE + TEXT_TYPE + COMMA_SEP +
                    ChatPersistenceContract.ChatEntry.COLUMN_NAME_TIMESTAMP + INTEGER_TYPE +

            " )";

    public ChatDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
