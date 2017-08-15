package com.test.antony.megakittest.di.module;

import android.app.Fragment;
import android.content.Context;

import com.test.antony.megakittest.di.ActivityContext;
import com.test.antony.megakittest.di.PerFragment;
import com.test.antony.megakittest.ui.fragments.autoFragment.AutoPresenter;
import com.test.antony.megakittest.ui.fragments.autoFragment.IAuto;
import com.test.antony.megakittest.ui.fragments.ownerFragment.IOwner;
import com.test.antony.megakittest.ui.fragments.ownerFragment.OwnerPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by admin on 11.08.17.
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
        mFragment=fragment;
    }

    @Provides
    @ActivityContext
    Context provideContext(){
        return mFragment.getActivity();
    }

    @Provides
    Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    @PerFragment
    IAuto.Presenter<IAuto.View> provideAutoPresenter(AutoPresenter<IAuto.View>  presenter){
        return presenter;
    }

    @Provides
    @PerFragment
    IOwner.Presenter<IOwner.View> provideOwnerPresenter(OwnerPresenter<IOwner.View> presenter){
        return presenter;
    }

}
