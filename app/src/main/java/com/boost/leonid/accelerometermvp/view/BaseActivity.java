package com.boost.leonid.accelerometermvp.view;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.boost.leonid.accelerometermvp.R;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    private ProgressDialog mProgressDialog;
    private Toolbar mToolbar;

    protected void initToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu: ");
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_start:
                Log.d(TAG, "menu_start: ");
                break;
            case R.id.menu_stop:
                Log.d(TAG, "menu_stop: ");
                break;
            case R.id.menu_logout:
                Log.d(TAG, "menu_logout: ");
                break;
            case R.id.menu_settings:
                Log.d(TAG, "menu_settings: ");
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showProgress() {
        if (mProgressDialog == null){
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading...");
        }
        mProgressDialog.show();
    }

    public void hideProgress() {
        if (mProgressDialog.isShowing() && mProgressDialog != null){
            mProgressDialog.dismiss();
        }
    }
}
