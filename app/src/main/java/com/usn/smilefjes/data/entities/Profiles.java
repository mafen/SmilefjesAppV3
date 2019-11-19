
package com.usn.smilefjes.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profiles {
    @SerializedName("entries")
    private List<Tilsyn> profiles;

    public List<Tilsyn> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Tilsyn> profiles) {
        this.profiles = profiles;
    }
}

