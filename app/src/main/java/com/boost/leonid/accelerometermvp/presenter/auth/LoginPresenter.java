package com.boost.leonid.accelerometermvp.presenter.auth;


public interface LoginPresenter {
    void onStart();

    void onLoginBtnClick(String email, String password);
    void onRegisterLabelClick();
}
