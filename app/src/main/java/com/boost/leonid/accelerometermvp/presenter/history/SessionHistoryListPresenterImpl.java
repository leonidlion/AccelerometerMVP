package com.boost.leonid.accelerometermvp.presenter.history;

import android.util.Log;

import com.boost.leonid.accelerometermvp.Constants;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.BasePresenter;
import com.boost.leonid.accelerometermvp.view.history.SessionHistoryListView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

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

/*        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                view().hideProgress();
                Log.d(TAG, "onDataChange: " + dataSnapshot.getRef());
                view().setQuery(dataSnapshot.getRef());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
                view().hideProgress();
            }
        });*/
        view().setQuery(reference);
    }

    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: " + position);
    }
}
