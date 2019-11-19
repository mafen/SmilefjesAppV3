package com.usn.smilefjes.data.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;


public class Tilsyn extends BaseObservable {

    private int orgnummer;
    private String navn;

    @SerializedName("adrlinje1")
    private String adresse;


    private int postnr;
    private String poststed;

    @SerializedName("total_karakter")
    private double totalKarakter;

    public Tilsyn(String navn, int orgnummer) {
        this.navn = navn;
        this.orgnummer = orgnummer;

    }
    @Bindable

    public int getOrgnummer() {
        return orgnummer;
    }

    public void setOrgnummer(int orgnummer) {
        notifyPropertyChanged(BR.orgnummer);
        this.orgnummer = orgnummer;
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
    public double getTotalKarakter() {
        return totalKarakter;
    }

    public void setTotalKarakter(double totalKarakter) {
        this.totalKarakter = totalKarakter;
    }





}

