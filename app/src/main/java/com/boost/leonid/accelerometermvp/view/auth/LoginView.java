package com.boost.leonid.accelerometermvp.view.auth;



public interface LoginView {
    void showProgress();
    void hideProgress();
    boolean validate();

    void goToRegisterActivity();

    void goToMainActivity();
}
