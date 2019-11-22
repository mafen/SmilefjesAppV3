
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

/**
 *
 */
public class TilsynRepository {

    SmilefjesApi smilefjesApi;

    private LiveData<Tilsyn> tilsynLiveData;

    public TilsynRepository(Application application) {



        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://hotell.difi.no/api/json/mattilsynet/smilefjes/");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();



        smilefjesApi = retrofit.create(SmilefjesApi.class);


    }


    /**
     * @param id
     * @param callback
     */
    public void lesEtTilsyn(String id, Callback<AlleTilsyn> callback) {
        Call<AlleTilsyn> call = smilefjesApi.lesEtTilsyn(id);
        call.enqueue(callback);
    }

    /**
     * @param callback
     */
    public void lesFlereTilsyn(Callback<AlleTilsyn> callback) {
        Call<AlleTilsyn> call = smilefjesApi.lesAlle();
        call.enqueue(callback);
    }










}

