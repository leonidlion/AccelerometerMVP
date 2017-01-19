package com.boost.leonid.accelerometermvp.adapter;

import android.util.Log;
import android.view.View;

import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;


public class HistoryAdapter extends FirebaseRecyclerAdapter<HistoryItem, HistoryHolder>{
    private static final String TAG = "HistoryAdapter";

    public HistoryAdapter(Class<HistoryItem> modelClass, int modelLayout, Class<HistoryHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        Log.d(TAG, "HistoryAdapter: ");
    }

    @Override
    protected void populateViewHolder(HistoryHolder viewHolder, final HistoryItem model, int position) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + model.getStartTime());
            }
        });
        viewHolder.bind(model);
    }
}