package com.boost.leonid.accelerometermvp.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.presenter.auth.LoginPresenter;
import com.boost.leonid.accelerometermvp.presenter.auth.LoginPresenterImpl;
import com.boost.leonid.accelerometermvp.view.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {
    private static final int LAYOUT = R.layout.activity_login;
    private LoginPresenter mLoginPresenter;

    @BindView(R.id.et_input_email)
    EditText mInputEmailEdit;
    @BindView(R.id.et_input_password)
    EditText mInputPassEdit;
    @BindView(R.id.btn_login)
    Button mLoginBtn;
    @BindView(R.id.link_signup)
    TextView mSignInLink;

    @OnClick(R.id.btn_login)
    public void onLoginClick(){
        mLoginPresenter.onLoginBtnClick(
                mInputEmailEdit.getText().toString(),
                mInputPassEdit.getText().toString());
    }

    @OnClick(R.id.link_signup)
    public void onRegisterLabelClick(){
        mLoginPresenter.onRegisterLabelClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLoginPresenter = new LoginPresenterImpl(this);
        mLoginPresenter.onStart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        ButterKnife.bind(this);
    }

    @Override
    public boolean validate(){
        boolean result = true;
        if (TextUtils.isEmpty(mInputEmailEdit.getText().toString())){
            mInputEmailEdit.setError(getString(R.string.required));
            result = false;
        } else {
            mInputEmailEdit.setError(null);
        }

        if (TextUtils.isEmpty(mInputPassEdit.getText().toString())){
            mInputPassEdit.setError(getString(R.string.required));
            result = false;
        } else {
            mInputPassEdit.setError(null);
        }
        return result;
    }

    @Override
    public void goToRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @Override
    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
