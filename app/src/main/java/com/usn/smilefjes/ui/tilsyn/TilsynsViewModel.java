package com.usn.smilefjes.ui.tilsyn;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.usn.smilefjes.data.entities.AlleTilsyn;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.data.repository.TilsynRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/*
Denne klassen står for mellom-lagring av Data før det vises til bruker
Dem bruker en Repository som igjen bruker retrofit til å hente data
 */

public class TilsynsViewModel extends ViewModel {

    private TilsynRepository tilsynRepository;
    public MutableLiveData<Tilsyn> tilsyn = new MutableLiveData<>() ;

    private MutableLiveData<List<Tilsyn>> tilsynListe = new MutableLiveData<>();
    private MutableLiveData<List<Tilsyn>> tilsynListePost = new MutableLiveData<>();



    public TilsynsViewModel() {

        tilsynRepository = new TilsynRepository();

        tilsynRepository.lesFlereTilsyn(new Callback<AlleTilsyn>() {
            @Override
            public void onResponse(Call<AlleTilsyn> call, @NotNull Response<AlleTilsyn> response) {
                assert response.body() != null;
                List<Tilsyn> body = response.body().getTilsynList();

                tilsynListe.setValue(body);
            }

            @Override
            public void onFailure(Call<AlleTilsyn> call, Throwable t) {
                tilsynListe.setValue(new ArrayList<>(Collections.singleton(new Tilsyn("Ingen data funnet", 3))));
                Log.e("API", "onFailure: failed to read tilsyn", t);
            }
        });


    }



    public  LiveData<List<Tilsyn>> getTilsyn1(String id){

        tilsynRepository.lesEtTilsyn(id, new Callback<AlleTilsyn>() {
            @Override
            public void onResponse(Call<AlleTilsyn> call, Response<AlleTilsyn> response) {

                if (response.isSuccessful()) {

                    List<Tilsyn> body = response.body().getTilsynList();

                    tilsynListe.setValue(body);


                }
            }



            @Override
            public void onFailure(Call<AlleTilsyn> call, Throwable t) {
                Log.e("API", "onFailure: failed to read tilsyn", t);
            }
        });
        return tilsynListe;
    }


    public  LiveData<List<Tilsyn>> getTilsynMedAar(String id){

        tilsynRepository.lesTilsynMedAar(id, new Callback<AlleTilsyn>() {
            @Override
            public void onResponse(Call<AlleTilsyn> call, Response<AlleTilsyn> response) {

                if (response.isSuccessful()) {

                    Log.d("Adress", response.toString());
                    List<Tilsyn> body = response.body().getTilsynList();
                    tilsynListe.setValue(body);


                }
            }



            @Override
            public void onFailure(Call<AlleTilsyn> call, Throwable t) {
                Log.e("API", "onFailure: failed to read tilsyn", t);
            }
        });
        return tilsynListe;
    }


    public LiveData<List<Tilsyn>> getTilsynListe() {
        return tilsynListe;
    }

    public void setTilsynListe(MutableLiveData<List<Tilsyn>> tilsynListe) {
        this.tilsynListe = tilsynListe;
    }



    public void setTilsyn(MutableLiveData<Tilsyn> tilsyn) {
        this.tilsyn = tilsyn;
    }


}
