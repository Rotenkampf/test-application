/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.ui.fragments.ownerFragment;

import com.test.antony.megakittest.data.IDataManager;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Antony Mosin
 */

public class OwnerPresenter<V extends IOwner.View> extends BasePresenter<V> implements IOwner.Presenter<V>{


    @Inject
    public OwnerPresenter(IDataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void saveOwner(OwnerData ownerData) {

    }

    @Override
    public void loadAutos() {

    }

    @Override
    public void loadOwners() {

    }
}
