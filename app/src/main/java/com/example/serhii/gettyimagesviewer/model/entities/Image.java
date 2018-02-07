package com.example.serhii.gettyimagesviewer.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Required;

/**
 * Created by Serhii on 06.02.2018.
 */

public class Image extends RealmObject {
    @Ignore
    @SerializedName("display_sizes")
    private List<DisplaySize> displaySizes = null;

    @Required
    private String phrase;

    @Required
    private String url;

    public List<DisplaySize> getDisplaySizes() {
        return displaySizes;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
