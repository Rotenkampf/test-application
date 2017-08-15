package com.test.antony.megakittest.ui.fragments.autoFragment;

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

public class AutoPresenter<V extends IAuto.View> extends BasePresenter<V> implements IAuto.Presenter<V> {


    @Inject
    public AutoPresenter(IDataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void saveAuto(AutoData autoData) {
        getDataManager().addAuto(autoData);
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
}
