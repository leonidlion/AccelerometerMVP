package com.boost.leonid.accelerometermvp.presenter.auth;

import android.support.annotation.NonNull;
import android.util.Log;

import com.boost.leonid.accelerometermvp.model.User;
import com.boost.leonid.accelerometermvp.presenter.BasePresenter;
import com.boost.leonid.accelerometermvp.view.auth.LoginView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginPresenterImpl extends BasePresenter<User, LoginView> implements LoginPresenter {
    private FirebaseAuth mAuth;

    public LoginPresenterImpl(LoginView view) {
        bindView(view);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        if (mAuth.getCurrentUser() != null){
            view().goToMainActivity();
        }
    }

    @Override
    public void onLoginBtnClick(String email, String password) {
        if (view().validate()){
            view().showProgress();
            mAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           // TODO: 20.01.17 next line can produce NullPointerEx if view == null
                           view().hideProgress();
                           if (task.isSuccessful()){
                               Log.d("PRESENTER", "Login success");
                               view().goToMainActivity();
                           }else {
                               Log.d("PRESENTER", "Login failure");
                           }
                       }
                   });
        }
    }

    @Override
    public void onRegisterLabelClick() {
        view().goToRegisterActivity();
    }

    @Override
    protected void updateView() {

    }
}
