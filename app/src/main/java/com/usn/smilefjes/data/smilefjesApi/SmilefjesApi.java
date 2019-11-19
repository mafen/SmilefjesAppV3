
package com.usn.smilefjes.data.smilefjesApi;

import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.data.entities.Profiles;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SmilefjesApi {
    @GET("tilsyn?")
    Call<Profiles> readProfiles();

    @GET("tilsyn?{username}")
    Call<Tilsyn> readProfile(@Path("username") String username);



}

