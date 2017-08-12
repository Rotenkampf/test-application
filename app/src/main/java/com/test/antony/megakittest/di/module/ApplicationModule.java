package com.test.antony.megakittest.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.test.antony.megakittest.data.DataManager;
import com.test.antony.megakittest.data.IDataManager;
import com.test.antony.megakittest.data.db.DatabaseManager;
import com.test.antony.megakittest.data.db.IDatabaseManager;
import com.test.antony.megakittest.data.network.INetworkManager;
import com.test.antony.megakittest.data.network.NetworkManager;
import com.test.antony.megakittest.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by admin on 11.08.17.
 */

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application app){
        mApplication=app;
    }

    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }

    @Provides
    Application provideApplication(){
        return mApplication;
    }


    @Provides
    @Singleton
    IDataManager provideDataManager(DataManager dataManager){
        return dataManager;
    }

    @Provides
    @Singleton
    IDatabaseManager provideDatabaseManager(DatabaseManager databaseManager){
        return databaseManager;
    }

    @Provides
    @Singleton
    INetworkManager provideNetworkManager(NetworkManager networkManager){
        return networkManager;
    }

}
