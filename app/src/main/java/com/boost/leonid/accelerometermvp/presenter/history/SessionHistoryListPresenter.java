package com.boost.leonid.accelerometermvp.presenter.history;


import com.google.firebase.database.DatabaseReference;

public interface SessionHistoryListPresenter {

    void onCreate();

    void onItemClick(DatabaseReference ref);
}