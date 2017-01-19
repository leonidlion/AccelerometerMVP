package com.boost.leonid.accelerometermvp.view.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.adapter.TabPagerAdapter;
import com.boost.leonid.accelerometermvp.model.HistoryItem;
import com.boost.leonid.accelerometermvp.view.BaseActivity;
import com.boost.leonid.accelerometermvp.view.history.SessionHistoryListActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TabListGraphActivity extends BaseActivity {
    private static final String TAG = "TabListGraphActivity";
    private static final int LAYOUT = R.layout.activity_tab_list_graph;
    public static final String EXTRA_REFERENCE = "EXTRA_REFERENCE";
    private TabPagerAdapter mPagerAdapter;

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

        mPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), getResources().getStringArray(R.array.tab_titles), null);

        String path = getIntent().getStringExtra(EXTRA_REFERENCE);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: ");
                if (mPagerAdapter != null){
                    mPagerAdapter.setNewData(dataSnapshot.getValue(HistoryItem.class).getCoordinates());
                    mPagerAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: ");
            }
        });

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
