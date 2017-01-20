package com.boost.leonid.accelerometermvp.presenter;

import android.support.annotation.NonNull;

import java.lang.ref.WeakReference;

/**
 *
 * @param <M> - model
 * @param <V> - view
 */

public abstract class BasePresenter<M, V> {
    protected M model;
    private WeakReference<V> view;

    public void setModel(M model) {
        resetState();
        this.model = model;
        if (setupDone()) {
            updateView();
        }
    }

    protected void resetState() {
    }

    public void bindView(@NonNull V view) {
        this.view = new WeakReference<>(view);
        if (setupDone()) {
            updateView();
        }
    }

    public void unbindView() {
        this.view = null;
    }

    protected V view() {
        // TODO: 20.01.17 redundant code, view.get() returns null if view == null
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }

    protected abstract void updateView();

    protected boolean setupDone() {
        return view() != null && model != null;
    }
}
