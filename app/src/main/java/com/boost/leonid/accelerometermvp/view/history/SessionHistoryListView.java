package com.boost.leonid.accelerometermvp.view.history;


import com.google.firebase.database.DatabaseReference;

public interface SessionHistoryListView {
    void setQuery(DatabaseReference databaseReference);
    void showProgress();
    void hideProgress();
}
