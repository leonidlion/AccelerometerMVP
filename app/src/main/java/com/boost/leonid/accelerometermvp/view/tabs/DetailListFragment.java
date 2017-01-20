package com.boost.leonid.accelerometermvp.view.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.model.AccelerometerData;

import java.util.ArrayList;
import java.util.List;

public class DetailListFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_detail_list;
    private static final String TAG = "DetailListFragment";


    public static DetailListFragment newInstance(List<AccelerometerData> data) {
        Bundle args = new Bundle();
        DetailListFragment fragment = new DetailListFragment();
        if (data != null) {
            args.putParcelableArrayList("KEY_D", new ArrayList<>(data));
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        if (getArguments() != null) {
            /// TODO: 20.01.17 String Constant
            List<AccelerometerData> list = getArguments().getParcelableArrayList("KEY_D");
            for (AccelerometerData x : list) {
                Log.d(TAG, "onCreateView: " + x.getX());
            }
        }
        return view;
    }
}
