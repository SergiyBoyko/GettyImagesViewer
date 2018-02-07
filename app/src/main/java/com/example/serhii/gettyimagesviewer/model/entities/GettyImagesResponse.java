package com.example.serhii.gettyimagesviewer.model.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Serhii on 06.02.2018.
 */

public class GettyImagesResponse {
    @SerializedName("images")
    private List<Image> images = null;

    public List<Image> getImages() {
        return images;
    }

}
