
package com.usn.smilefjes.data.repository;

import android.util.Log;

import com.usn.smilefjes.data.entities.AlleKrav;
import com.usn.smilefjes.data.entities.AlleTilsyn;
import com.usn.smilefjes.data.smilefjesApi.SmilefjesApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 */
public class TilsynRepository {

    private SmilefjesApi smilefjesApi;

    public TilsynRepository() {


        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://hotell.difi.no/api/json/mattilsynet/smilefjes/");
        builder.addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder
                .build();

        smilefjesApi = retrofit.create(SmilefjesApi.class);
    }



    public void lesEtTilsyn(String id, Callback<AlleTilsyn> callback) {
        Call<AlleTilsyn> call = smilefjesApi.lesEtTilsyn(id);
        call.enqueue(callback);
    }

    /**
     * @param callback det som kommer tilbake fra api kallet blir lagret her
     */
    public void lesFlereTilsyn(Callback<AlleTilsyn> callback) {
        Call<AlleTilsyn> call = smilefjesApi.lesAlle();
        call.enqueue(callback);
    }


    public void lesTilsynMedAar(String id, Callback<AlleTilsyn> callback) {
        Call<AlleTilsyn> call = smilefjesApi.lesAllMed(id);
        call.enqueue(callback);
    }


    /**
     * @param callback
     */
    public void lesKrav(String id, Callback<AlleKrav> callback) {
        Call<AlleKrav> call = smilefjesApi.lesAlleKrav(id);
        call.enqueue(callback);
    }











}

