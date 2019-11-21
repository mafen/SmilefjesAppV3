package com.usn.smilefjes.data.entities;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.widget.ImageViewCompat;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;
import com.usn.smilefjes.R;

import java.io.Serializable;

public class Tilsyn extends BaseObservable implements Serializable {

    @SerializedName("orgnummer")
    private int orgnummer;

    @SerializedName("tilsynid")
    private String tilsynid;

    @SerializedName("navn")
    private String navn;

    private int imageSrc;

    @SerializedName("adrlinje1")
    private String adresse;

    @SerializedName("postnr")
    private int postnr;

    @SerializedName("poststed")
    private String poststed;

    @SerializedName("total_karakter")
    private int totalKarakter;

    public Tilsyn(String navn, int orgnummer) {
        this.navn = navn;
        this.orgnummer = orgnummer;

    }

    @Bindable
    public int getImageSrc() {
        if (totalKarakter <= 1 )
            imageSrc = R.drawable.ic_satisfied_24dp;
        else if (totalKarakter == 2)
            imageSrc = R.drawable.ic_neutral_24dp;
            else
            imageSrc =R.drawable.ic_dissatisfied_24dp;
            
        return imageSrc;
    }

    @Bindable
    public int getOrgnummer() {
        return orgnummer;
    }

    public void setOrgnummer(int orgnummer) {
        notifyPropertyChanged(BR.orgnummer);
        this.orgnummer = orgnummer;
    }

    public String getTilsynid() {
        return tilsynid;
    }

    @Bindable
    public String getNavn() {
        return navn;
    }


    public void setNavn(String navn) {
        notifyPropertyChanged(BR.navn);
        this.navn = navn;
    }

    @Bindable
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        notifyPropertyChanged(BR.adresse);
        this.adresse = adresse;
    }


    @Bindable
    public int getPostnr() {
        return postnr;
    }

    public void setPostnr(int postnr) {
        notifyPropertyChanged(BR.postnr);
        this.postnr = postnr;
    }

    @Bindable
    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    @Bindable
    public int getTotalKarakter() {
        return totalKarakter;
    }

    public void setTotalKarakter(int totalKarakter) {
        this.totalKarakter = totalKarakter;
    }



    }








