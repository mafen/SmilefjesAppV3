
package com.usn.smilefjes.data.smilefjesApi;

import com.usn.smilefjes.data.entities.AlleTilsyn;
import com.usn.smilefjes.data.entities.Tilsyn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SmilefjesApi {
    @GET("tilsyn?")
    Call<AlleTilsyn> readProfiles();

    @GET("tilsyn?{username}")
    Call<Tilsyn> readProfile(@Path("username") String username);



}

