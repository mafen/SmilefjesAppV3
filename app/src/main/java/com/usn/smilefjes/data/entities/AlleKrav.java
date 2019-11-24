
package com.usn.smilefjes.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class AlleKrav {
    @SerializedName("entries")
    private List<Kravpunkt> kravList;

    public List<Kravpunkt> getKravList() {
        return kravList;
    }

}

