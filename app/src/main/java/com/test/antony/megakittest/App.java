package com.test.antony.megakittest;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.test.antony.megakittest.data.IDataManager;
import com.test.antony.megakittest.di.component.ApplicationComponent;
import com.test.antony.megakittest.di.component.DaggerApplicationComponent;
import com.test.antony.megakittest.di.module.ApplicationModule;

import javax.inject.Inject;

/**
 * Created by admin on 11.08.17.
 */

public class App extends Application {

    protected ApplicationComponent mApplicationComponent;

    @Inject
    IDataManager mDataManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent= DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        mApplicationComponent.inject(this);
    }

    public static App get(Context context){
        return (App) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

}
