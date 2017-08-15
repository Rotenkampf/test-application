/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.ui.view.creatorController;

import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.test.antony.megakittest.R;
import com.test.antony.megakittest.utils.listeners.CreatorListener;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Antony Mosin
 */

public abstract class AbstractCreatorController<V, K> {

    private View mMainView;
    private View mNestedView;

    private V mSavedData;

    private boolean mIsNested;

    private CreatorListener mCreatorListener;

    public AbstractCreatorController(View mainView, @Nullable View nestedView) {
        mMainView = mainView;
        mNestedView = nestedView;
        bindViews();
        setupListeners();
    }

    protected abstract void bindViews();

    protected void setupListeners(){
        mMainView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        setupCustomListeners();
        if (mNestedView!=null){
            setupNestedListeners();
        }
    }

    protected abstract void setupCustomListeners();

    protected abstract void setupNestedListeners();

    public void setData(V data){
        mSavedData=data;
    }

    public abstract void setDataSet(List<K> dataSet);

    protected void hideNestedView(){
        mNestedView.startAnimation(AnimationUtils.loadAnimation(mNestedView.getContext(), R.anim.slide_out_down));
        mNestedView.setVisibility(View.GONE);
        mMainView.setVisibility(View.VISIBLE);
    }

    public void setCreatorListener(CreatorListener creatorListener) {
        mCreatorListener = creatorListener;
    }

    public abstract Observable<V> registerSaveObservable();

    protected abstract V formSaveData();

    protected abstract boolean isValid();

    public abstract void cancel();

    public abstract void cancelNested();

    protected boolean isNested() {
        return mIsNested;
    }

    public void setNested(boolean nested) {
        mIsNested = nested;
    }

    protected View getMainView() {
        return mMainView;
    }

    protected View getNestedView() {
        return mNestedView;
    }


    protected CreatorListener getCreatorListener() {
        return mCreatorListener;
    }

    public V getSavedData() {
        return mSavedData;
    }

    public void setSavedData(V savedData) {
        mSavedData = savedData;
    }
}
