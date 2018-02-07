package com.example.serhii.gettyimagesviewer.ui.activities;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serhii.gettyimagesviewer.AppGettyImages;
import com.example.serhii.gettyimagesviewer.R;
import com.example.serhii.gettyimagesviewer.di.component.AppComponent;
import com.example.serhii.gettyimagesviewer.di.component.DaggerPresentersComponent;
import com.example.serhii.gettyimagesviewer.di.module.PresentersModule;
import com.example.serhii.gettyimagesviewer.model.entities.Image;
import com.example.serhii.gettyimagesviewer.presenters.LoadImagePresenter;
import com.example.serhii.gettyimagesviewer.views.LoadImageView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, LoadImageView {

    @Inject
    LoadImagePresenter loadImagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        loadImagePresenter.setView(this);

        initToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        SearchView mSearchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        //set search view edit text color
        EditText searchEditText = (EditText) mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorPrimary));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorPrimary));
        //set max width
        mSearchView.setMaxWidth(Integer.MAX_VALUE);
        //set edit listener
        mSearchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        showText("onQueryTextSubmit");
        loadImagePresenter.getFeaturedContent(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void addHistory(Image image, String phrase) {
        showText("Good Result: " + phrase);
    }

    /*
     * *********************************************************************************************
     * private methods
     * *********************************************************************************************
     */

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private AppComponent getAppComponent() {
        return getApp().appComponent();
    }

    private AppGettyImages getApp() {
        return (AppGettyImages) getApplication();
    }

    private void showText(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
