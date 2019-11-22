
package com.usn.smilefjes.data.smilefjesApi;

import com.usn.smilefjes.data.entities.AlleTilsyn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SmilefjesApi {
    @GET("tilsyn?")
    Call<AlleTilsyn> lesAlle();

    @GET("tilsyn?")
    Call<AlleTilsyn> lesEtTilsyn(@Query("tilsynid") String verdi);



}

