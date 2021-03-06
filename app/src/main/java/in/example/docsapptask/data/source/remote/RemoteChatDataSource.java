package in.example.docsapptask.data.source.remote;

import android.accounts.NetworkErrorException;
import android.app.Service;

import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.models.MessageType;
import in.example.docsapptask.data.response.ServiceResponse;
import in.example.docsapptask.data.source.ChatDataSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class RemoteChatDataSource {

    private static final String API_URL = "http://www.personalityforge.com/";
    private static final String API_KEY = "6nt5d1nJHkqbkphe";
    private static final String EXTERNAL_ID = "chirag1";

    private Retrofit mRetrofit;
    private static RemoteChatDataSource INSTANCE;

    private RemoteChatDataSource() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API_URL)
                .build();
    }

    public static RemoteChatDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteChatDataSource();
        }

        return INSTANCE;

    }

    private interface ChatAPI {
        @GET("api/chat/")
        Single<ServiceResponse> getChat(@Query("apiKey") String apiKey
                , @Query("chatBotID") long chatBotID
                , @Query("externalID") String externalId
                , @Query("message") String message);
    }

    public Single<Message> sendMessage(Message message) {
        ChatAPI chatAPI = mRetrofit.create(ChatAPI.class);
        return chatAPI.getChat(API_KEY, message.getChatBotID(), EXTERNAL_ID, message.getMessage())
                .flatMap(new Function<ServiceResponse, SingleSource<? extends Message>>() {
                    @Override
                    public SingleSource<? extends Message> apply(ServiceResponse serviceResponse) throws Exception {
                        if (serviceResponse.getSuccess() == 1 && serviceResponse.getErrorMessage
                                ().isEmpty()) {
                            Message msg = serviceResponse.getMessage();
                            msg.setMessageType(MessageType.RECEIVED);
                            msg.setTimestamp(System.currentTimeMillis());
                            return Single.just(msg);
                        }
                        return Single.error(new NetworkErrorException(serviceResponse
                                .getErrorMessage()));
                    }
                });
    }
}
