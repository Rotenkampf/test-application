package com.test.antony.megakittest.data.db;

import android.content.Context;

import com.test.antony.megakittest.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by admin on 11.08.17.
 */

@Singleton
public class DatabaseManager implements IDatabaseManager{


    private RealmConfiguration mConfiguration;
    private Realm mRealm;

    @Inject
    public DatabaseManager(@ApplicationContext Context context) {
        Realm.init(context);
        mConfiguration = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.compactRealm(mConfiguration);
        mRealm=Realm.getInstance(mConfiguration);
    }



}
