package com.boost.leonid.accelerometermvp.view.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.adapter.TabPagerAdapter;
import com.boost.leonid.accelerometermvp.view.BaseActivity;
import com.boost.leonid.accelerometermvp.view.history.SessionHistoryListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabListGraphActivity extends BaseActivity  {
    private static final String TAG = "TabListGraphActivity";
    private static final int LAYOUT = R.layout.activity_tab_list_graph;
    public static final String EXTRA_REFERENCE = "EXTRA_REFERENCE";

    public interface DataFirebase{
        void getData();
    }
    @BindView(R.id.main_pager)
    ViewPager mViewPager;

    @BindView(R.id.main_tabs)
    TabLayout mTabLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        ButterKnife.bind(this);
        initToolbar();

        TabPagerAdapter mPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.tab_titles));
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, SessionHistoryListActivity.class));
        finish();
    }
}
