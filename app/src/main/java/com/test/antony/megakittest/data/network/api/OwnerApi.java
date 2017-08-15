/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.data.network.api;

import com.test.antony.megakittest.data.db.model.AutoData;
import com.test.antony.megakittest.data.db.model.OwnerData;

import java.util.List;

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
    Call<List<AutoData>> getAutos();

    @PUT("api/owner")
    Call<ResponseBody> sendOwner(@Body OwnerData ownerData);

    @DELETE("api/owner")
    Call<ResponseBody> deleteOwner(@Query("id") String id);


}
