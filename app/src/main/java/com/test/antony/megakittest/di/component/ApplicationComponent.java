package com.test.antony.megakittest.di.component;

import android.app.Application;
import android.content.Context;

import com.test.antony.megakittest.App;
import com.test.antony.megakittest.data.IDataManager;
import com.test.antony.megakittest.data.db.IDatabaseManager;
import com.test.antony.megakittest.data.network.INetworkManager;
import com.test.antony.megakittest.di.ApplicationContext;
import com.test.antony.megakittest.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Antony Mosin
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(App app);

    @ApplicationContext
    Context getContext();

    Application getApplication();

    IDataManager getDataManager();

    IDatabaseManager getDatabaseManager();

    INetworkManager getNetworkManager();
}