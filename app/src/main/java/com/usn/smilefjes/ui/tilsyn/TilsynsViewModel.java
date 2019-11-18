package com.usn.smilefjes.ui.tilsyn;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.usn.smilefjes.data.entities.Tilsyn;


import java.util.ArrayList;
import java.util.List;



public class TilsynsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Tilsyn>> tilsynListe = new MutableLiveData<List<Tilsyn>>();



    public TilsynsViewModel(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<List<Tilsyn>> getTilsynListe() {
        return tilsynListe;
    }
}
