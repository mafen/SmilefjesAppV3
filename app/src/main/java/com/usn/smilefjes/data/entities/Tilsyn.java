package com.usn.smilefjes.data.entities;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;


public class Tilsyn extends BaseObservable {

    private int orgNr;
    private String navn;
    private String adresse;
    private int postNr;
    private String postSted;
    private double totalKarakter;

    public Tilsyn(String navn, int orgNr) {
        this.navn = navn;
        this.orgNr = orgNr;

    }

    public int getOrgNr() {
        return orgNr;
    }

    public void setOrgNr(int orgNr) {
        this.orgNr = orgNr;
    }

    @Bindable
    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        notifyPropertyChanged(BR.navn);
        this.navn = navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public int getPostNr() {
        return postNr;
    }

    public void setPostNr(int postNr) {
        this.postNr = postNr;
    }

    public String getPostSted() {
        return postSted;
    }

    public void setPostSted(String postSted) {
        this.postSted = postSted;
    }

    public double getTotalKarakter() {
        return totalKarakter;
    }

    public void setTotalKarakter(double totalKarakter) {
        this.totalKarakter = totalKarakter;
    }





}

