/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.data.network.api;

import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Antony Mosin
 */

public interface OwnerApi {

    @GET("api/owner")
    Observable<List<AutoData>> getAutos();

    @PUT("api/owner")
    Observable<ResponseBody> sendOwner(@Body OwnerData ownerData);

    @DELETE("api/owner")
    Observable<ResponseBody> deleteOwner(@Query("id") String id);


}
