package com.usn.smilefjes.data.entities;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class Kravpunkt extends BaseObservable  {

    @SerializedName("tilsynid")
    private String tilsynid;

    @SerializedName("karakter")
    private int karakter;

    @SerializedName("ordningsverdi")
    private int ordningsverdi;

    @SerializedName("kravpunktnavn_no")
    private String kravpunktNavn;

    @SerializedName("tekst_no")
    private String kravTekst;


    public Kravpunkt(String kravpunktNavn){
        this.kravpunktNavn = kravpunktNavn;

    }

    public String getTilsynid() {
        return tilsynid;
    }

    public int getKarakter() {
        return karakter;
    }

    public int getOrdningsverdi() {
        return ordningsverdi;
    }

    public String getKravpunktNavn() {
        return kravpunktNavn;
    }

    public String getKravTekst() {
        return kravTekst;
    }
}
