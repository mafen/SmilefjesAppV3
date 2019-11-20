package com.usn.smilefjes.ui.tilsyn;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;


import com.usn.smilefjes.data.entities.AlleTilsyn;
import com.usn.smilefjes.data.entities.Tilsyn;
import com.usn.smilefjes.data.repository.TilsynRepository;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TilsynsViewModel extends AndroidViewModel {

    private TilsynRepository tilsynRepository;
    private MutableLiveData<List<Tilsyn>> tilsynListe = new MutableLiveData<List<Tilsyn>>();


    public TilsynsViewModel(@NonNull Application application) {
        super(application);

        tilsynRepository = new TilsynRepository(application);

        tilsynRepository.lesFlereTilsyn(new Callback<AlleTilsyn>() {
            @Override
            public void onResponse(Call<AlleTilsyn> call, Response<AlleTilsyn> response) {
                List<Tilsyn> body = response.body().getTilsynList();

                tilsynListe.setValue(body);
            }

            @Override
            public void onFailure(Call<AlleTilsyn> call, Throwable t) {
                tilsynListe.setValue(new ArrayList<Tilsyn>());
                Log.e("API", "onFailure: failed to read tilsyn", t);
            }
        });

    }


    public LiveData<List<Tilsyn>> getTilsynListe() {
        return tilsynListe;
    }
}
