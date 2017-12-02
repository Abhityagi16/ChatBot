package in.example.docsapptask.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import in.example.docsapptask.R;
import in.example.docsapptask.data.models.Message;

public class ChatActivity extends AppCompatActivity implements ChatActivityContract.View {

    private RecyclerView mRecyclerView;
    private ChatAdapter mAdapter;
    private ProgressBar mProgress;

    private ChatActivityContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mPresenter = new ChatListPresenter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager
                .VERTICAL, false);

        mRecyclerView = (RecyclerView) findViewById(R.id.chat_list);
        mAdapter = new ChatAdapter(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void addMessage(Message message) {
        mAdapter.addMessage(message);
    }
}
