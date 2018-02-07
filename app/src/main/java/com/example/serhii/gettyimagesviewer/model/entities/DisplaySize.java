package com.example.serhii.gettyimagesviewer.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Serhii on 06.02.2018.
 */

public class DisplaySize {

    @SerializedName("uri")
    private String uri;

    public String getUri() {
        return uri;
    }

}
