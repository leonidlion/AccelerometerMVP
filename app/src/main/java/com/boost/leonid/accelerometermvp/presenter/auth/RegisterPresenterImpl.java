package com.boost.leonid.accelerometermvp.presenter.auth;


import android.support.annotation.NonNull;
import android.util.Log;

import com.boost.leonid.accelerometermvp.Constants;
import com.boost.leonid.accelerometermvp.model.User;
import com.boost.leonid.accelerometermvp.presenter.BasePresenter;
import com.boost.leonid.accelerometermvp.view.auth.RegisterView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPresenterImpl extends BasePresenter<User, RegisterView> implements RegisterPresenter {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    public RegisterPresenterImpl(RegisterView registerView) {
        bindView(registerView);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onSignUpClick(final String name, final String email, final String password) {
        if (view().validData()){
            view().showProgress();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            view().hideProgress();
                            if (task.isSuccessful()){
                                createNewUser(task.getResult().getUser().getUid(), name, email);
                            }else {
                                Log.d("PRESENTER", "NOT SUCCESS ");
                            }
                        }
                    });
        }
    }

    private void createNewUser(String uid, String name, String email) {
        User user = new User(name, email);
        mDatabase.child(Constants.CHILD_USERS).child(uid).setValue(user);
    }

    @Override
    public void onLoginLabelClick() {
        view().goToLoginActivity();
    }

    @Override
    protected void updateView() {

    }
}
