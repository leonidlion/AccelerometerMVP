package com.boost.leonid.accelerometermvp.view.auth;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.boost.leonid.accelerometermvp.R;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;
    private Toolbar mToolbar;

    protected void initToolbar(){
        mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
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
