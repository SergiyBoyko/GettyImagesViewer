package com.example.serhii.gettyimagesviewer.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Serhii on 06.02.2018.
 */

public class Image {
    @SerializedName("id")
    private String id;

    @SerializedName("display_sizes")
    private List<DisplaySize> displaySizes = null;

    public String getId() {
        return id;
    }

    public List<DisplaySize> getDisplaySizes() {
        return displaySizes;
    }

}
