package in.example.docsapptask.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.models.MessageType;
import in.example.docsapptask.data.source.ChatDataSource;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by abhishektyagi on 02/12/17.
 */


public class LocalChatDataSource {

    private static LocalChatDataSource INSTANCE;
    private ChatDbHelper mDbHelper;
    private SQLiteDatabase mDb;

    private LocalChatDataSource(Context context) {
        mDbHelper = new ChatDbHelper(context);
    }

    public static LocalChatDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalChatDataSource(context);
        }

        return INSTANCE;

    }

    public Single<List<Message>> loadMessages() {

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ChatPersistenceContract.ChatEntry.COLUMN_NAME_MESSAGE,
                ChatPersistenceContract.ChatEntry.COLUMN_NAME_MESSAGE_TYPE,
                ChatPersistenceContract.ChatEntry.COLUMN_NAME_TIMESTAMP
        };

        String sortOrder =
                ChatPersistenceContract.ChatEntry.COLUMN_NAME_TIMESTAMP + " ASC";

        Cursor cursor = db.query(
                ChatPersistenceContract.ChatEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );
        List<Message> messages = new ArrayList<>();
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    long timeStamp = cursor.getLong(
                            cursor.getColumnIndexOrThrow(ChatPersistenceContract.ChatEntry.COLUMN_NAME_TIMESTAMP));
                    String message = cursor.getString(cursor.getColumnIndex(ChatPersistenceContract.ChatEntry
                            .COLUMN_NAME_MESSAGE));
                    int messageType = cursor.getInt(cursor.getColumnIndex(ChatPersistenceContract
                            .ChatEntry.COLUMN_NAME_MESSAGE_TYPE));
                    Message msg = new Message(-1, message);
                    msg.setMessageType(MessageType.getEnumById(messageType));
                    msg.setTimestamp(timeStamp);
                    messages.add(msg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();
                db.close();
            }
        }

        return Single.just(messages);

    }
    public void closeDatabase() {
        mDb.close();
    }

    public void saveMessage(Message message) {
        if (mDb == null) {
            mDb = mDbHelper.getWritableDatabase();
        }
        ContentValues values = new ContentValues();
        values.put(ChatPersistenceContract.ChatEntry.COLUMN_NAME_MESSAGE_TYPE, message
                .getMessageType().getId());
        values.put(ChatPersistenceContract.ChatEntry.COLUMN_NAME_MESSAGE, message.getMessage());
        values.put(ChatPersistenceContract.ChatEntry.COLUMN_NAME_TIMESTAMP, message.getTimestamp());
        mDb.insert(ChatPersistenceContract.ChatEntry.TABLE_NAME, null, values);
    }
}
