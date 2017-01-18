package com.boost.leonid.accelerometermvp.adapter;

import android.support.annotation.NonNull;

import com.boost.leonid.accelerometermvp.presenter.BasePresenter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;


public abstract class MvpRecyclerAdapter<M, P extends BasePresenter, VH extends MvpViewHolder> extends FirebaseRecyclerAdapter<M, VH> {
    protected final Map<Object, P> presenters;

    public MvpRecyclerAdapter(Class<M> modelClass, int modelLayout, Class<VH> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        presenters = new HashMap<>();
    }

    @NonNull
    protected P getPresenter(@NonNull M model) {
        System.err.println("Getting presenter for item " + getModelId(model));
        return presenters.get(getModelId(model));
    }

    @NonNull protected abstract P createPresenter(@NonNull M model);

    @NonNull protected abstract Object getModelId(@NonNull M model);


    @Override
    public void onViewRecycled(VH holder) {
        super.onViewRecycled(holder);

        holder.unbindPresenter();
    }

    @Override
    public boolean onFailedToRecycleView(VH holder) {
        holder.unbindPresenter();

        return super.onFailedToRecycleView(holder);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bindPresenter(getPresenter(getItem(position)));
    }

    public abstract M getItem(int position);

    @Override
    protected void populateViewHolder(VH viewHolder, M model, int position) {

    }
}
