package com.boost.leonid.accelerometermvp.view.history;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.adapter.HistoryHolder;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.history.SessionHistoryListPresenter;
import com.boost.leonid.accelerometermvp.presenter.history.SessionHistoryListPresenterImpl;
import com.boost.leonid.accelerometermvp.view.BaseActivity;
import com.boost.leonid.accelerometermvp.view.tabs.TabListGraphActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;


public class SessionHistoryListActivity extends BaseActivity implements SessionHistoryListView {
    private static final int LAYOUT = R.layout.activity_session_history;
    private static final String TAG = "SessionHistoryListActiv";
    private RecyclerView mRecyclerView;
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
        // TODO: 20.01.17  ButterKnife
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_session_history);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void setAdapter(final Query query){
        showProgress();
        // TODO: 20.01.17   Is better to create Class that extends from FirebaseRecyclerAdapter and set it to your RecyclerView,
        FirebaseRecyclerAdapter<HistoryItem, HistoryHolder> adapter = new FirebaseRecyclerAdapter<HistoryItem, HistoryHolder>(HistoryItem.class, R.layout.sesstion_history_item, HistoryHolder.class, query) {
            @Override
            protected void populateViewHolder(HistoryHolder viewHolder, final HistoryItem model, final int position) {
                viewHolder.bind(model);
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.onItemClick(getRef(position));
                    }
                });
                hideProgress();
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void startActivityTab(DatabaseReference ref) {
        Intent intent = new Intent(this, TabListGraphActivity.class);
        intent.putExtra(TabListGraphActivity.EXTRA_REFERENCE, ref.toString());
        startActivity(intent);
        finish();
    }
}