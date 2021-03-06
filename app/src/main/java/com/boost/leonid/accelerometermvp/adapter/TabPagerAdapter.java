package com.boost.leonid.accelerometermvp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.boost.leonid.accelerometermvp.view.tabs.DetailListFragment;
import com.boost.leonid.accelerometermvp.view.tabs.GraphDataFragment;


public class TabPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "TabPagerAdapter";
    private static final int DETAIL_LIST_FRAGMENT   = 0;
    private static final int GRAPH_VIEW_FRAGMENT    = 1;

    private final String[] mTabTitles;
    private final int NUM_TAB;
    private String mPath;

    public TabPagerAdapter(FragmentManager fm, String[] titles, String path) {
        super(fm);
        mTabTitles = titles;
        NUM_TAB = titles.length;
        mPath = path;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case DETAIL_LIST_FRAGMENT:
                Log.d(TAG, "getItem: detail");
                fragment = DetailListFragment.newInstance(mPath);
                break;
            case GRAPH_VIEW_FRAGMENT:
                Log.d(TAG, "getItem: graph");
                fragment = GraphDataFragment.newInstance(mPath);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_TAB;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }

}
