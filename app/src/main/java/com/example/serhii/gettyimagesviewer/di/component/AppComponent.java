package com.example.serhii.gettyimagesviewer.di.component;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.serhii.gettyimagesviewer.di.module.ApiModule;
import com.example.serhii.gettyimagesviewer.di.module.AppModule;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Serhii on 06.02.2018.
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ApiModule.class
})
public interface AppComponent extends ApiComponent {

    Context context();

    SharedPreferences preferences();

    Executor executor();

}
