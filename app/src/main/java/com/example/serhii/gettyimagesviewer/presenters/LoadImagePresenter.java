package com.example.serhii.gettyimagesviewer.presenters;

import com.example.serhii.gettyimagesviewer.model.ILoadImageDataSource;
import com.example.serhii.gettyimagesviewer.views.LoadImageView;

/**
 * Created by Serhii on 06.02.2018.
 */

public class LoadImagePresenter extends BasePresenter<LoadImageView> {
    private final ILoadImageDataSource loadImageDataSource;

    public LoadImagePresenter(ILoadImageDataSource loadImageDataSource) {
        this.loadImageDataSource = loadImageDataSource;
    }
}
