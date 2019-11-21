
package com.usn.smilefjes.data.smilefjesApi;

import com.usn.smilefjes.data.entities.AlleTilsyn;
import com.usn.smilefjes.data.entities.Tilsyn;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryName;

public interface SmilefjesApi {
    @GET("tilsyn?dato=19112019")
    Call<AlleTilsyn> lesAlle();

    @GET("tilsyn?")
    Call<AlleTilsyn> lesEtTilsyn(@Query("tilsynid") String verdi);



}

