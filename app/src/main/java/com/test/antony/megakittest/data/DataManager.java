package com.test.antony.megakittest.data;

import android.content.Context;

import com.test.antony.megakittest.data.db.IDatabaseManager;
import com.test.antony.megakittest.data.network.INetworkManager;
import com.test.antony.megakittest.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by admin on 11.08.17.
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




}
