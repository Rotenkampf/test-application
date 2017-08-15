package com.test.antony.megakittest.data.db;

import java.util.List;

import io.reactivex.Observable;
import io.realm.RealmObject;

/**
 * Created by Antony Mosin
 */

public interface IDatabaseManager {

    void addToDatabase(RealmObject realmObject);

    <V extends RealmObject> Observable<List<V>> getData(Class<V> clazz);

    <V extends RealmObject> void deleteFromDatabase(Class<V> clazz, String objectId);
}
