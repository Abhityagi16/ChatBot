package in.example.docsapptask.chat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import in.example.docsapptask.R;
import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.models.MessageType;
import in.example.docsapptask.data.response.ServiceResponse;

/**
 * Created by abhishektyagi on 02/12/17.
 */

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private Context mContext;
    private ServiceResponse serviceResponse;
    private List<Message> mMessages;

    public ChatAdapter(Context context) {
        this.mContext = context;
        mMessages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        mMessages.add(message);
        notifyItemInserted(mMessages.size()-1);
    }

    public void setMessages(List<Message> msgs) {
        mMessages = msgs;
    }


    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == MessageType.SENT.getId()) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_sent,
                    parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_received,
                    parent, false);
        }

        return new ChatViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).getMessageType().getId();
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Message msg = mMessages.get(position);
        holder.message.setText(msg.getMessage());
    }


    @Override
    public int getItemCount() {

        return mMessages == null ? 0 : mMessages.size();
    }

    class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView message;

        public ChatViewHolder(View itemView) {

            super(itemView);
            message = itemView.findViewById(R.id.message);
        }
    }
}
