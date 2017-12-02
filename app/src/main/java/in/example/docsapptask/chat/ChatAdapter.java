package in.example.docsapptask.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import in.example.docsapptask.R;
import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.response.ServiceResponse;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context mContext;
    private ServiceResponse serviceResponse;
    List<Message> mMessages;

    public ChatAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight_row,
                parent, false);

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {

        return mMessages == null ? 0 : mMessages.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {


        public ChatViewHolder(View itemView) {
            super(itemView);
        }
    }
}
