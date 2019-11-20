
package com.usn.smilefjes.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.usn.smilefjes.data.entities.AlleTilsyn;
import com.usn.smilefjes.data.entities.Tilsyn;
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
                .baseUrl("https://hotell.difi.no/api/json/mattilsynet/smilefjes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        smilefjesApi = retrofit.create(SmilefjesApi.class);


    }


    public void lesEtTilsyn(String id, Callback<Tilsyn> callback) {
        Call<Tilsyn> call = smilefjesApi.readProfile(id);
        call.enqueue(callback);
    }

    public void lesFlereTilsyn(Callback<AlleTilsyn> callback) {
        Call<AlleTilsyn> call = smilefjesApi.readProfiles();
        call.enqueue(callback);
    }










}

