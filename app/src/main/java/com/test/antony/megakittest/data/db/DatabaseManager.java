package com.test.antony.megakittest.data.db;

import android.content.Context;

import com.test.antony.megakittest.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by Antony Mosin
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

    @Override
    public void addToDatabase(final RealmObject realmObject) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmObject);
            }
        });
    }


    @Override
    public <V extends RealmObject> Observable<List<V>> getData(final Class<V> clazz){
        return Observable.create(new ObservableOnSubscribe<List<V>>() {
            @Override
            public void subscribe(ObservableEmitter<List<V>> e) throws Exception {
                Realm realm=Realm.getInstance(mConfiguration);
                RealmResults<V> realmResults=realm.where(clazz).findAll();
                e.onNext(realm.copyFromRealm(realmResults));
                realm.close();
            }
        });
    }

}
