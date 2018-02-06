package com.example.serhii.gettyimagesviewer.di.module;

import com.example.serhii.gettyimagesviewer.di.scope.Scope;
import com.example.serhii.gettyimagesviewer.di.scope.Scopes;
import com.example.serhii.gettyimagesviewer.model.ILoadImageDataSource;
import com.example.serhii.gettyimagesviewer.presenters.LoadImagePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Serhii on 06.02.2018.
 */

@Module
public class PresentersModule {

    @Provides
    @Scope(Scopes.VIEW)
    public LoadImagePresenter provideLoadImagePresenter(ILoadImageDataSource iLoadImageDataSource) {
        return new LoadImagePresenter(iLoadImageDataSource);
    }
}
