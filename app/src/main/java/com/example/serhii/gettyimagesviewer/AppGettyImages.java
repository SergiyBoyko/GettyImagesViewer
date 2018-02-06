package com.example.serhii.gettyimagesviewer;

import android.support.multidex.MultiDexApplication;

import com.example.serhii.gettyimagesviewer.di.component.AppComponent;
import com.example.serhii.gettyimagesviewer.di.component.DaggerAppComponent;
import com.example.serhii.gettyimagesviewer.di.module.ApiModule;
import com.example.serhii.gettyimagesviewer.di.module.AppModule;

/**
 * Created by Serhii on 06.02.2018.
 */

public class AppGettyImages extends MultiDexApplication {

    private AppComponent appComponent;

    public AppGettyImages() {
        super();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent appComponent() {
        return appComponent;
    }

}
