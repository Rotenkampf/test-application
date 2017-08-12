package com.test.antony.megakittest.ui.base;

import com.test.antony.megakittest.data.IDataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by admin on 11.08.17.
 */

public class BasePresenter<V extends IBase.View> implements IBase.Presenter<V> {

    private final IDataManager mDataManager;
    private final CompositeDisposable mCompositeDisposable;
    private V mView;


    @Inject
    public BasePresenter(IDataManager dataManager, CompositeDisposable compositeDisposable){
        mDataManager=dataManager;
        mCompositeDisposable=compositeDisposable;
    }

    @Override
    public void onAttach(V view) {
        mView=view;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mView=null;
    }

    public V getView(){
        return mView;
    }

    public IDataManager getDataManager() {
        return mDataManager;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

}
