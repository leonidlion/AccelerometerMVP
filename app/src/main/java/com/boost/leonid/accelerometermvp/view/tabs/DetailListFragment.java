package com.boost.leonid.accelerometermvp.view.tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boost.leonid.accelerometermvp.R;

public class DetailListFragment extends Fragment {
    private static final int LAYOUT = R.layout.fragment_detail_list;
    private static final String TAG = "DetailListFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(LAYOUT, container, false);

        return view;
    }
}
