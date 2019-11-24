package com.usn.smilefjes.data.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("unused")
public class Tilsyn extends BaseObservable implements Comparable<Tilsyn> , Serializable {

    @SerializedName("orgnummer")
    private int orgnummer;

    @SerializedName("tilsynid")
    private String tilsynid;

    @SerializedName("navn")
    private String navn;

    @SerializedName("adrlinje1")
    private String adresse;

    @SerializedName("postnr")
    private int postnr;

    @SerializedName("poststed")
    private String poststed;

    @SerializedName("dato")
    private String dato;

    @SerializedName("total_karakter")
    private int totalKarakter;

    @SerializedName("tema1_no")
    private String tema1;

    @SerializedName("tema2_no")
    private String tema2;

    @SerializedName("tema3_no")
    private String tema3;

    @SerializedName("tema4_no")
    private String tema4;

    @SerializedName("karakter1")
    private int tema1Krakater;

    @SerializedName("karakter2")
    private int tema2Krakater;

    @SerializedName("karakter3")
    private int tema3Krakater;

    @SerializedName("karakter4")
    private int tema4Krakater;

    private Date DatoFormatert;


    public Tilsyn(String navn) {
        this.navn = navn;

    }

    public Tilsyn(String navn, int totalKarakter) {
        this.navn = navn;
        this.totalKarakter = totalKarakter;
    }

    public int getTema1Krakater() {
        return tema1Krakater;
    }

    public int getTema2Krakater() {
        return tema2Krakater;
    }

    public int getTema3Krakater() {
        return tema3Krakater;
    }

    public int getTema4Krakater() {
        return tema4Krakater;
    }

    @SuppressWarnings("WeakerAccess")
    public Date getDatoFormatert() {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMy");
            DatoFormatert = dateFormat.parse(getDato());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return DatoFormatert;
    }

    @SuppressWarnings("WeakerAccess")
    @Bindable
    public String getDato() {
        return dato;
    }

    @Bindable
    public int getOrgnummer() {
        return orgnummer;
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


    @Bindable
    public int getPostnr() {
        return postnr;
    }


    @Bindable
    public String getPoststed() {
        return poststed;
    }

    @Bindable
    public int getTotalKarakter() {
        return totalKarakter;
    }


    public String getTema1() {
        return tema1;
    }

    public String getTema2() {
        return tema2;
    }

    public String getTema3() {
        return tema3;
    }

    public String getTema4() {
        return tema4;
    }

    @Override
    public int compareTo(@NotNull Tilsyn o) {

        if(  this.getDatoFormatert() == null || o.getDatoFormatert() == null){
            return 0;
        }
            return getDatoFormatert().compareTo(o.getDatoFormatert());
    }


}








