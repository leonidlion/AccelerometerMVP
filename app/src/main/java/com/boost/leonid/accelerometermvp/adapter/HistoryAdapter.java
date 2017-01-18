package com.boost.leonid.accelerometermvp.adapter;

import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


public class HistoryAdapter extends FirebaseRecyclerAdapter<HistoryItem, HistoryHolder> {

    public HistoryAdapter(Class<HistoryItem> modelClass, int modelLayout, Class<HistoryHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(HistoryHolder viewHolder, HistoryItem model, int position) {

        viewHolder.bind(model);
    }
}
