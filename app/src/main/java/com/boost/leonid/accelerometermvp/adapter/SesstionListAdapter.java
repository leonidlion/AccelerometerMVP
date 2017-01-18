package com.boost.leonid.accelerometermvp.adapter;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.presenter.history.SessionHistoryListPresenterImpl;
import com.boost.leonid.accelerometermvp.view.history.SessionHistoryListView;
import com.google.firebase.database.Query;


public class SesstionListAdapter extends MvpRecyclerAdapter<HistoryItem, SessionHistoryListPresenterImpl, SesstionListAdapter.HistoryHolder> {


    public SesstionListAdapter(Class<HistoryItem> modelClass, int modelLayout, Class<HistoryHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HistoryHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sesstion_history_item, parent, false));
    }

    @NonNull
    @Override
    protected SessionHistoryListPresenterImpl createPresenter(@NonNull HistoryItem model) {
        SessionHistoryListPresenterImpl presenter = new SessionHistoryListPresenterImpl();
        presenter.setModel(model);
        return presenter;
    }

    @NonNull
    @Override
    protected Object getModelId(@NonNull HistoryItem model) {
        return model.getStartTime();
    }

    @Override
    public HistoryItem getItem(int position) {
        return null;
    }

    // =========== HOLDER ===============
    public class HistoryHolder extends MvpViewHolder<SessionHistoryListPresenterImpl> implements SessionHistoryListView {
        private TextView mDate;
        private TextView mTime;

        public HistoryHolder(final View itemView) {
            super(itemView);
            mDate = (TextView) itemView.findViewById(R.id.list_date);
            mTime = (TextView) itemView.findViewById(R.id.list_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("Onclick", "CLICK");
                }
            });
        }

        public void bind(HistoryItem historyItem){
            mDate.setText(historyItem.getStartDate());
            mTime.setText(historyItem.getStartTime());
        }

        @Override
        public void setRecyclerView(HistoryItem item) {
            bind(item);
        }
    }
}
