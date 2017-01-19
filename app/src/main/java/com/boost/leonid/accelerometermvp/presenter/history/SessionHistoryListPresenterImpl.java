package com.boost.leonid.accelerometermvp.presenter.history;

import com.boost.leonid.accelerometermvp.Constants;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.BasePresenter;
import com.boost.leonid.accelerometermvp.view.history.SessionHistoryListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class SessionHistoryListPresenterImpl extends BasePresenter<HistoryItem, SessionHistoryListView> implements SessionHistoryListPresenter {
    private static final String TAG = "HistoryPresenter";

    public SessionHistoryListPresenterImpl(SessionHistoryListView view) {
        bindView(view);
    }

    @Override
    protected void updateView() {

    }

    @Override
    public void getQuery() {
        DatabaseReference reference = Constants.getDataCoordinatesOfUserReference(
                FirebaseAuth.getInstance().getCurrentUser().getUid());
        view().setQuery(reference);
    }

}
