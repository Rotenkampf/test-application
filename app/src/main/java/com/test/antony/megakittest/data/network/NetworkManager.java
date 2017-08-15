package com.test.antony.megakittest.data.network;

import android.content.Context;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.network.api.AutoApi;
import com.test.antony.megakittest.data.network.api.OwnerApi;
import com.test.antony.megakittest.data.network.typeAdapter.AutoTypeAdapter;
import com.test.antony.megakittest.di.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Antony Mosin
 */

@Singleton
public class NetworkManager implements INetworkManager {

    private static final String BASE_URL="https://UnknownAPI.com/";

    private Context mContext;
    private AutoApi mAutoApi;
    private OwnerApi mOwnerApi;

    @Inject
    public NetworkManager(@ApplicationContext Context context){
        mContext=context;
        GsonBuilder builder=new GsonBuilder();
        builder.registerTypeAdapter(AutoData.class, new AutoTypeAdapter());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(builder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mOwnerApi=retrofit.create(OwnerApi.class);
        mAutoApi=retrofit.create(AutoApi.class);

    }

    @Override
    public void sendAuto(AutoData autoData) {
        mAutoApi
                .sendAuto(autoData)
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(@NonNull ResponseBody responseBody) throws Exception {
                        Log.d("retrofit", responseBody.string());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.d("retrofit", throwable.getMessage());
                    }
                });
    }
}
