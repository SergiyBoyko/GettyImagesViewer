package com.example.serhii.gettyimagesviewer.views;

import com.example.serhii.gettyimagesviewer.model.entities.Image;

/**
 * Created by Serhii on 06.02.2018.
 */

public interface LoadImageView extends BaseView {

    void addHistory(Image image, String phrase);

}
