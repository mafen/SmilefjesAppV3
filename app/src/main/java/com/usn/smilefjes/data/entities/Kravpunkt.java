package com.usn.smilefjes.data.entities;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class Kravpunkt extends BaseObservable implements Comparable<Kravpunkt> {

    @SerializedName("tilsynid")
    private String tilsynid;

    @SerializedName("karakter")
    private int karakter;

    @SerializedName("ordningsverdi")
    private String ordningsverdi;

    @SerializedName("kravpunktnavn_no")
    private String kravpunktNavn;

    @SerializedName("tekst_no")
    private String kravTekst;

    private String navn;

    public Kravpunkt(String kravpunktNavn){
        this.kravpunktNavn = kravpunktNavn;

    }

    public String getNavn(){

        navn = ordningsverdi + " " + kravpunktNavn;
        return navn;
    }

    public String getTilsynid() {
        return tilsynid;
    }

    public int getKarakter() {
        return karakter;
    }

    public String getOrdningsverdi() {
        return ordningsverdi;
    }

    public String getKravpunktNavn() {
        return kravpunktNavn;
    }

    public String getKravTekst() {
        return kravTekst;
    }

    @Override
    public int compareTo(Kravpunkt o) {

        if(  this.karakter > o.karakter){
            return 1;
        }
        else if( this.karakter < o.karakter)
            return -1;
        return 0;
    }

}
