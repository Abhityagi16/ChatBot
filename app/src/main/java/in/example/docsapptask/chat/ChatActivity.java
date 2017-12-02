package in.example.docsapptask.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import in.example.docsapptask.R;
import in.example.docsapptask.data.models.Message;
import in.example.docsapptask.data.source.DaoManager;

public class ChatActivity extends AppCompatActivity implements ChatActivityContract.View, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private ProgressBar mProgress;
    private TextView send;
    private EditText msgBox;

    private ChatActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mPresenter = new ChatListPresenter(this, DaoManager.getChatDao(this));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false);

        mRecyclerView = findViewById(R.id.chat_list);
        msgBox = findViewById(R.id.msg_box);
        send = findViewById(R.id.send);
        send.setOnClickListener(this);
        mAdapter = new ChatAdapter(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mPresenter.loadSavedMessages();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.send) {
            String text = msgBox.getText().toString();
            if (text.length() > 0) {
                mPresenter.sendMessage(text);
            }
        }
    }

    @Override
    public void setMessages(List<Message> messages) {
        mAdapter.setMessages(messages);
        mRecyclerView.scrollToPosition(messages.size()-1);
    }

    @Override
    public void clearMessage() {
        msgBox.setText("");
    }

    @Override
    public void showErrorToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.releaseResources();
    }

    @Override
    public void addMessage(Message message) {
        mAdapter.addMessage(message);
        mRecyclerView.scrollToPosition(mAdapter.getItemCount()-1);
    }
}
