package com.boost.leonid.accelerometermvp.view.history;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boost.leonid.accelerometermvp.Constants;
import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.adapter.SesstionListAdapter;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.view.auth.BaseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.Query;


public class SessionHistoryListActivity extends BaseActivity {
    private static final int LAYOUT = R.layout.activity_session_history;
    private SesstionListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);

        Query query = Constants.getDataCoordinatesOfUserReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
        mAdapter = new SesstionListAdapter(HistoryItem.class, R.layout.sesstion_history_item, SesstionListAdapter.HistoryHolder.class, query);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_session_history);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
    }

}
