package com.boost.leonid.accelerometermvp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.model.HistoryItem;


public class HistoryHolder extends RecyclerView.ViewHolder {
    private TextView mDate;
    private TextView mTime;

    public HistoryHolder(final View itemView) {
        super(itemView);
        mDate = (TextView) itemView.findViewById(R.id.list_date);
        mTime = (TextView) itemView.findViewById(R.id.list_time);
    }

    public void bind(HistoryItem historyItem) {
        mDate.setText(historyItem.getStartDate());
        mTime.setText(historyItem.getStartTime());
    }
}
