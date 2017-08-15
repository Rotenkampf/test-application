/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.ui.fragments.ownerFragment;

import com.test.antony.megakittest.data.IDataManager;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.ui.base.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        getDataManager().addOwner(ownerData);
    }

    @Override
    public void loadAutos() {
        getCompositeDisposable().add(
                getDataManager()
                        .getAutos()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<AutoData>>() {
                            @Override
                            public void accept(@NonNull List<AutoData> autoDatas) throws Exception {
                                getView().setAutos(autoDatas);
                            }
                        })
        );
    }

    @Override
    public void loadOwners() {
        getCompositeDisposable().add(
                getDataManager()
                        .getOwners()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<OwnerData>>() {
                            @Override
                            public void accept(@NonNull List<OwnerData> ownerDatas) throws Exception {
                                getView().setOwners(ownerDatas);
                            }
                        })
        );
    }

    @Override
    public void deleteOwner(String ownerId) {
        getDataManager().deleteOwner(ownerId);
    }
}
