package in.example.docsapptask.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import in.example.docsapptask.data.models.Message;
import io.reactivex.Single;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by abhishektyagi on 15/08/17.
 */

public class ChatDao implements ChatDataSource {

    @Nullable
    private static ChatDao INSTANCE = null;
    private final ChatDataSource mFlightsRemoteDataSource;

    private final ChatDataSource mFlightsLocalDataSource;        //Since we are not storing
    // data in local db this variable won't be used
    Message mCachedResponse;        //We'll keep data in cache in case of device rotation

    boolean mIsCacheDirty = false;

    private ChatDao(@NonNull ChatDataSource flightsRemoteDataSource,
                       @NonNull ChatDataSource flightsLocalDataSource) {
        mFlightsRemoteDataSource = checkNotNull(flightsRemoteDataSource);
        mFlightsLocalDataSource = checkNotNull(flightsLocalDataSource);
    }

    public static ChatDao getInstance(@NonNull ChatDataSource flightsRemoteDataSource,
                                         @NonNull ChatDataSource flightsLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ChatDao(flightsRemoteDataSource, flightsLocalDataSource);
        }
        return INSTANCE;
    }

    @Override
    public Single<Message> getResponse(Message message) {
        return null;
    }
}
