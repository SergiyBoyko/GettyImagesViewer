package com.example.serhii.gettyimagesviewer.di.module;

import com.example.serhii.gettyimagesviewer.api.GettyImagesApi;
import com.example.serhii.gettyimagesviewer.common.Constants;
import com.example.serhii.gettyimagesviewer.model.ILoadImageDataSource;
import com.example.serhii.gettyimagesviewer.model.remote.LoadImageRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Serhii on 06.02.2018.
 */

@Module
public class ApiModule {

    @Provides
    @Singleton
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.GETTY_IMAGES_API)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ILoadImageDataSource provideFeaturedDataSource(Retrofit retrofit) {
        return new LoadImageRemoteDataSource(retrofit.create(GettyImagesApi.class));
    }

}
