package com.usn.smilefjes.ui.kravpunkt;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.usn.smilefjes.data.entities.AlleKrav;
import com.usn.smilefjes.data.entities.Kravpunkt;
import com.usn.smilefjes.data.repository.TilsynRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class KravViewModel extends ViewModel {

    // Denne klassen gjør det sammen som den andre view moddelen
    // Men den gjør det for krav punkt objektet
    // Det henter ned data fra apiet til matilsynet og lagrer i en liste.

    private final TilsynRepository kravRepository;
    private MutableLiveData<List<Kravpunkt>> kravListe = new MutableLiveData<>();

    public KravViewModel() {
        kravRepository = new TilsynRepository();
    }

    public LiveData<List<Kravpunkt>> getKravListe(String id) {

        kravRepository.lesKrav(id, new Callback<AlleKrav>() {
            @Override
            public void onResponse(Call<AlleKrav> call, @NotNull Response<AlleKrav> response) {

                if(response.isSuccessful()){
                    List<Kravpunkt> body = null;
                    if (response.body() != null) {
                        body = response.body().getKravList();
                    }
                    kravListe.setValue( body);
                }
            }

            @Override
            public void onFailure(Call<AlleKrav> call, Throwable t) {
                kravListe.setValue(new ArrayList<>(Collections.singleton(new Kravpunkt("Ingen data funnet"))));
                Log.e("API", "onFailure: failed to read krav", t);
            }
        });

        return kravListe;
    }

}
