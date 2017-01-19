package com.boost.leonid.accelerometermvp.view.history;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public interface SessionHistoryListView {

    void setRecycler();

    void setAdapter(Query query);

    void startActivityTab(DatabaseReference ref);
}