package com.usn.smilefjes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.usn.smilefjes.data.entities.Tilsyn;

public class MainViewModel extends ViewModel {


    LiveData<Tilsyn> tilsynLiveData;


    public MainViewModel() {



    }



}
