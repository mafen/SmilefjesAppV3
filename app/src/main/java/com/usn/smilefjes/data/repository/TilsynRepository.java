/*
package com.usn.smilefjes.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.data.entities.Profiles;
import com.usn.smilefjes.data.smilefjesApi.SmilefjesApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TilsynRepository {

    SmilefjesApi smilefjesApi;

    private LiveData<Tilsyn> tilsynLiveData;

    public TilsynRepository(Application application) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hotell.difi.no/api/json/mattilsynet/smilefjes/tilsyn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        smilefjesApi = retrofit.create(SmilefjesApi.class);


    }


    public void readOnlineProfile(String username, Callback<Tilsyn> callback) {
        Call<Tilsyn> call = smilefjesApi.readProfile(username);
        call.enqueue(callback);
    }

    public void readOnlineProfiles(Callback<Profiles> callback) {
        Call<Profiles> call = smilefjesApi.readProfiles();
        call.enqueue(callback);
    }










}
*/
