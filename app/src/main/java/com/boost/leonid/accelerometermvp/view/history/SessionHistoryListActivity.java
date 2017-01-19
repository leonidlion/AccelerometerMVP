package com.boost.leonid.accelerometermvp.view.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.adapter.HistoryHolder;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.history.SessionHistoryListPresenter;
import com.boost.leonid.accelerometermvp.presenter.history.SessionHistoryListPresenterImpl;
import com.boost.leonid.accelerometermvp.view.auth.BaseActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


public class SessionHistoryListActivity extends BaseActivity implements SessionHistoryListView {
    private static final int LAYOUT = R.layout.activity_session_history;
    private static final String TAG = "SessionHistoryListActiv";
    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<HistoryItem, HistoryHolder> mAdapter;

    private SessionHistoryListPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        initToolbar();
        mPresenter = new SessionHistoryListPresenterImpl(this);
        mPresenter.onCreate(); // view -> setRecycler, setAdapter
    }

    @Override
    public void setRecycler(){
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_session_history);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setAdapter(Query query){
        showProgress();
        mAdapter = new FirebaseRecyclerAdapter<HistoryItem, HistoryHolder>(HistoryItem.class, R.layout.sesstion_history_item, HistoryHolder.class, query) {
            @Override
            protected void populateViewHolder(HistoryHolder viewHolder, final HistoryItem model, int position) {
                viewHolder.bind(model);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d(TAG, "onClick: " + model.getStartTime());
                    }
                });
                hideProgress();
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_start:
                break;
            case R.id.menu_stop:
                break;
            case R.id.menu_logout:
                break;
            case R.id.menu_settings:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}