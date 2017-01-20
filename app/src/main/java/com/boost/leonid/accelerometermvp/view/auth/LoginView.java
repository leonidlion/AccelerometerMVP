package com.boost.leonid.accelerometermvp.view.auth;



public interface LoginView {
    //// TODO: 20.01.17 Better create base interface for common methods and extend it in specific one.
    void showProgress();
    void hideProgress();
    boolean validate();

    void goToRegisterActivity();

    void goToMainActivity();
}
