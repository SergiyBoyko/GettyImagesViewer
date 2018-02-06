package com.example.serhii.gettyimagesviewer.model;

import com.example.serhii.gettyimagesviewer.model.entities.GettyImagesResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by Serhii on 07.02.2018.
 */

public interface ILoadImageDataSource {
    Observable<GettyImagesResponse> getBestContent(Map<String, String> headers, int phrase);
}
