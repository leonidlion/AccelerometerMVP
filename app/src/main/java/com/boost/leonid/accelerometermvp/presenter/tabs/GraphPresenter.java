package com.boost.leonid.accelerometermvp.presenter.tabs;


public interface GraphPresenter {
    void onCreateView(String path);

    void xClick(boolean b);

    void yClick(boolean b);

    void zClick(boolean b);
}
