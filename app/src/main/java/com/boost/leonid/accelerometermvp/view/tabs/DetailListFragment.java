package com.boost.leonid.accelerometermvp.view.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.leonid.accelerometermvp.Constants;
import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.adapter.DetailHolder;
import com.boost.leonid.accelerometermvp.model.AccelerometerData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailListFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_detail_list;
    private static final String TAG = "DetailListFragment";
    private static final String EXTRA_DATA = "EXTRA_DATA";
    private FirebaseRecyclerAdapter<AccelerometerData, DetailHolder> mAdapter;

    @BindView(R.id.rv_detail)
    RecyclerView mRecyclerView;

    public static DetailListFragment newInstance(String path) {
        Bundle args = new Bundle();
        args.putString(EXTRA_DATA, path);
        DetailListFragment fragment = new DetailListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);
        ButterKnife.bind(this, view);

        String path = getArguments().getString(EXTRA_DATA);

        setRecyclerView();
        setFirebaseAdapter(path);

        return view;
    }

    private void setRecyclerView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
    }

    private void setFirebaseAdapter(String path){
        Query query = FirebaseDatabase.getInstance().getReferenceFromUrl(path).child(Constants.CHILD_COORDINATES);
        mAdapter = new FirebaseRecyclerAdapter<AccelerometerData, DetailHolder>(AccelerometerData.class, R.layout.detail_list_item, DetailHolder.class, query) {
            @Override
            protected void populateViewHolder(DetailHolder viewHolder, AccelerometerData model, final int position) {
                viewHolder.bind(model);
            }
        };
        mRecyclerView.setAdapter(mAdapter);
    }

}
