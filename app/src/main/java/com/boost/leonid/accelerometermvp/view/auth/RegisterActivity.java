package com.boost.leonid.accelerometermvp.view.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.boost.leonid.accelerometermvp.R;
import com.boost.leonid.accelerometermvp.presenter.auth.RegisterPresenter;
import com.boost.leonid.accelerometermvp.presenter.auth.RegisterPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {
    private static final int LAYOUT = R.layout.activity_register;
    private RegisterPresenter mPresenter;

    @BindView(R.id.input_name)
    EditText mInputNameEdit;
    @BindView(R.id.et_input_email)
    EditText mInputEmailEdit;
    @BindView(R.id.et_input_password)
    EditText mInputPasswordEdit;
    @BindView(R.id.btn_signup)
    Button mSignupBtn;
    @BindView(R.id.link_login)
    TextView mSignInLabel;

    @OnClick({R.id.btn_signup, R.id.link_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_signup:
                mPresenter.onSignUpClick(
                        mInputNameEdit.getText().toString(),
                        mInputEmailEdit.getText().toString(),
                        mInputPasswordEdit.getText().toString());
                break;
            case R.id.link_login:
                mPresenter.onLoginLabelClick();
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new RegisterPresenterImpl(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LAYOUT);
        ButterKnife.bind(this);
    }

    @Override
    public boolean validData() {
        boolean valid = true;

        String name = mInputNameEdit.getText().toString();
        String email = mInputEmailEdit.getText().toString();
        String password = mInputPasswordEdit.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            mInputNameEdit.setError(getString(R.string.name_input_error));
            valid = false;
        } else {
            mInputNameEdit.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mInputEmailEdit.setError(getString(R.string.email_input_error));
            valid = false;
        } else {
            mInputEmailEdit.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            mInputPasswordEdit.setError(getString(R.string.pass_input_error));
            valid = false;
        } else {
            mInputPasswordEdit.setError(null);
        }

        return valid;
    }

    @Override
    public void goToLoginActivity() {
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }
}
