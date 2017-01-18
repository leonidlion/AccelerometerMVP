package com.boost.leonid.accelerometermvp.view.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.adapter.HistoryAdapter;
import com.boost.leonid.accelerometermvp.adapter.HistoryHolder;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.history.SessionHistoryListPresenter;
import com.boost.leonid.accelerometermvp.presenter.history.SessionHistoryListPresenterImpl;
import com.boost.leonid.accelerometermvp.view.auth.BaseActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public class SessionHistoryListActivity extends BaseActivity implements SessionHistoryListView {
    private static final int LAYOUT = R.layout.activity_session_history;
    private HistoryAdapter mAdapter;
    private Query mQuery;
    private SessionHistoryListPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        mPresenter = new SessionHistoryListPresenterImpl(this);
        mPresenter.getQuery();

        mAdapter = new HistoryAdapter(
                HistoryItem.class,
                R.layout.sesstion_history_item,
                HistoryHolder.class,
                mQuery);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_session_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setQuery(DatabaseReference reference){
        mQuery = reference;
    }
}
