package com.example.serhii.gettyimagesviewer.di.component;

import com.example.serhii.gettyimagesviewer.model.ILoadImageDataSource;

import retrofit2.Retrofit;

/**
 * Created by Serhii on 06.02.2018.
 */

public interface ApiComponent {

    Retrofit retrofit();

    ILoadImageDataSource loadImageDataSource();

}
