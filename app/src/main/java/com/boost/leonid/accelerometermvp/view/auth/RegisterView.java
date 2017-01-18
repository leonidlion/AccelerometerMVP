package com.boost.leonid.accelerometermvp.view.auth;


public interface RegisterView {
    void showProgress();
    void hideProgress();

    boolean validData();

    void goToLoginActivity();
}
