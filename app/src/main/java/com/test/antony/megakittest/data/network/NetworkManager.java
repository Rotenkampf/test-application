package com.test.antony.megakittest.data.network;

import android.content.Context;

import com.test.antony.megakittest.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by admin on 11.08.17.
 */

@Singleton
public class NetworkManager implements INetworkManager {

    private Context mContext;

    @Inject
    public NetworkManager(@ApplicationContext Context context){
        mContext=context;
    }

}
