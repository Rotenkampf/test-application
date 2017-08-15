/*
 * Copyright (c) 2017.
 * Antony Mosin mossanva@gmail.com
 */

package com.test.antony.megakittest.data.network.api;

import com.test.antony.megakittest.data.db.model.AutoData;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by Antony Mosin
 */

public interface AutoApi {

    @GET("api/auto")
    Observable<List<AutoData>> getAutos();

    @PUT("api/auto")
    Observable<ResponseBody> sendAuto(@Body AutoData autoData);

    @DELETE("api/auto")
    Observable<ResponseBody> deleteAuto(@Query("id") String id);

}
