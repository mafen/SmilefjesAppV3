
package com.usn.smilefjes.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class AlleTilsyn {
    @SerializedName("entries")
    private List<Tilsyn> tilsynList;

    public List<Tilsyn> getTilsynList() {
        return tilsynList;
    }

}

