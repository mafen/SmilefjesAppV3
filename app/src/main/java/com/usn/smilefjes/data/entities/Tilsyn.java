package com.usn.smilefjes.data.entities;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;
import com.usn.smilefjes.R;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Tilsyn extends BaseObservable implements Comparable<Tilsyn> , Serializable {

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


    private SimpleDateFormat dateFormat;

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

    @Bindable
    public int getImageSrc() {
        if (totalKarakter <= 1  )
            imageSrc = R.drawable.ic_satisfied_24dp;
        else if (totalKarakter == 2)
            imageSrc = R.drawable.ic_neutral_24dp;
            else
            imageSrc =R.drawable.ic_dissatisfied_24dp;
            
        return imageSrc;
    }



    public Date getDatoFormatert() {

        dateFormat = new SimpleDateFormat("ddMMy");
        //dateFormat.setTimeZone(TimeZone.getTimeZone("ECT"));


        try {
            DatoFormatert = dateFormat.parse(getDato());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return DatoFormatert;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    @Bindable
    public String getDato() {
        return dato;
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


    public String getTema1() {
        return tema1;
    }

    public void setTema1(String tema1) {
        this.tema1 = tema1;
    }

    public String getTema2() {
        return tema2;
    }

    public void setTema2(String tema2) {
        this.tema2 = tema2;
    }

    public String getTema3() {
        return tema3;
    }

    public void setTema3(String tema3) {
        this.tema3 = tema3;
    }

    public String getTema4() {
        return tema4;
    }

    public void setTema4(String tema4) {
        this.tema4 = tema4;
    }



    @Override
    public int compareTo(Tilsyn o) {

        if(  this.getDatoFormatert() == null || o.getDatoFormatert() == null){
            return 0;
        }
            return getDatoFormatert().compareTo(o.getDatoFormatert());
    }


}








