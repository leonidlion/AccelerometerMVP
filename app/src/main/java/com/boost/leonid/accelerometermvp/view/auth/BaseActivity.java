package com.boost.leonid.accelerometermvp.view.auth;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

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
