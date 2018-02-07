package com.example.serhii.gettyimagesviewer.model.remote;

import com.example.serhii.gettyimagesviewer.api.GettyImagesApi;
import com.example.serhii.gettyimagesviewer.model.ILoadImageDataSource;
import com.example.serhii.gettyimagesviewer.model.entities.GettyImagesResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by Serhii on 07.02.2018.
 */

public class LoadImageRemoteDataSource implements ILoadImageDataSource {

    private GettyImagesApi gettyImagesApi;

    public LoadImageRemoteDataSource(GettyImagesApi gettyImagesApi) {
        this.gettyImagesApi = gettyImagesApi;
    }

    @Override
    public Observable<GettyImagesResponse> getBestContent(String phrase) {
        return gettyImagesApi.getBestContent(phrase);
    }

}
