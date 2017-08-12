package com.test.antony.megakittest.ui.fragments.autoFragment;

import com.test.antony.megakittest.data.IDataManager;
import com.test.antony.megakittest.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by admin on 11.08.17.
 */

public class AutoPresenter<V extends IAuto.View> extends BasePresenter<V> implements IAuto.Presenter<V> {


    @Inject
    public AutoPresenter(IDataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }





}
