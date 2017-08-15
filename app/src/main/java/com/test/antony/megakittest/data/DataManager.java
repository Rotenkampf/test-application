package com.test.antony.megakittest.data;

import android.content.Context;

import com.test.antony.megakittest.data.db.IDatabaseManager;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;
import com.test.antony.megakittest.data.network.INetworkManager;
import com.test.antony.megakittest.di.ApplicationContext;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by Antony Mosin
 */

@Singleton
public class DataManager implements IDataManager {

    private Context mContext;
    private IDatabaseManager mDatabaseManager;
    private INetworkManager mNetworkManager;

    @Inject
    public DataManager(@ApplicationContext Context context,
                       IDatabaseManager databaseManager,
                       INetworkManager networkManager){
        mContext=context;
        mDatabaseManager=databaseManager;
        mNetworkManager=networkManager;
    }


    @Override
    public void addAuto(AutoData autoData) {
        mDatabaseManager.addToDatabase(autoData);
        mNetworkManager.sendAuto(autoData);
    }

    @Override
    public Observable<List<AutoData>> getAutos() {
        return mDatabaseManager.getData(AutoData.class);
    }

    @Override
    public Observable<List<OwnerData>> getOwners() {
        return mDatabaseManager.getData(OwnerData.class);
    }
}
