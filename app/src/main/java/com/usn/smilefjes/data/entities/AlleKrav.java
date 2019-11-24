
package com.usn.smilefjes.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlleKrav {
    @SerializedName("entries")
    private List<Kravpunkt> kravList;

    public List<Kravpunkt> getKravList() {
        return kravList;
    }

    public void setTilsynList(List<Kravpunkt> kravList) {
        this.kravList = kravList;
    }
}

