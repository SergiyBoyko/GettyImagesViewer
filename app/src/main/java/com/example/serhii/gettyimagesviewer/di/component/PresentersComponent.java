package com.example.serhii.gettyimagesviewer.di.component;

import com.example.serhii.gettyimagesviewer.di.module.PresentersModule;
import com.example.serhii.gettyimagesviewer.di.scope.Scope;
import com.example.serhii.gettyimagesviewer.di.scope.Scopes;
import com.example.serhii.gettyimagesviewer.ui.activities.MainActivity;

import dagger.Component;

/**
 * Created by Serhii on 06.02.2018.
 */

@Scope(Scopes.VIEW)
@Component(
        modules = {PresentersModule.class},
        dependencies = {AppComponent.class}
)
public interface PresentersComponent {

    void inject(MainActivity mainActivity);

}
