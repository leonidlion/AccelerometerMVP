package com.boost.leonid.accelerometermvp.view.history;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public interface SessionHistoryListView {
    void setQuery(DatabaseReference databaseReference);
    void showProgress();
    void hideProgress();
    void onItemClick(Query query);
}
