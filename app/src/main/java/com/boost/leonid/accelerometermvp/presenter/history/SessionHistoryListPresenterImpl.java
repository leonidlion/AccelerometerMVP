package com.boost.leonid.accelerometermvp.presenter.history;

import android.content.Intent;

import com.boost.leonid.accelerometermvp.Constants;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.BasePresenter;
import com.boost.leonid.accelerometermvp.view.history.SessionHistoryListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SessionHistoryListPresenterImpl extends BasePresenter<HistoryItem, SessionHistoryListView> implements SessionHistoryListPresenter {
    private static final String TAG = "HistoryPresenter";
    private DatabaseReference mReference;


    public SessionHistoryListPresenterImpl(SessionHistoryListView view) {
        bindView(view);
        mReference = Constants.getDataCoordinatesOfUserReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
    }

    @Override
    protected void updateView() {
    }

    @Override
    public void onCreate() {
        view().setRecycler();
        view().setAdapter(mReference);
    }

    @Override
    public void onItemClick(DatabaseReference ref) {
        view().startActivityTab(ref);
    }
}
