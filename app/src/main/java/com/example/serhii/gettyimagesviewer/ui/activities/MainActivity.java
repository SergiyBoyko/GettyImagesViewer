package com.example.serhii.gettyimagesviewer.ui.activities;

import android.content.Context;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.example.serhii.gettyimagesviewer.AppGettyImages;
import com.example.serhii.gettyimagesviewer.R;
import com.example.serhii.gettyimagesviewer.common.Constants;
import com.example.serhii.gettyimagesviewer.di.component.AppComponent;
import com.example.serhii.gettyimagesviewer.di.component.DaggerPresentersComponent;
import com.example.serhii.gettyimagesviewer.di.module.PresentersModule;
import com.example.serhii.gettyimagesviewer.model.entities.HistoryElement;
import com.example.serhii.gettyimagesviewer.model.entities.Image;
import com.example.serhii.gettyimagesviewer.presenters.LoadImagePresenter;
import com.example.serhii.gettyimagesviewer.views.LoadImageView;
import com.example.serhii.gettyimagesviewer.widgets.adapters.HistoryListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, LoadImageView,
        HistoryListAdapter.OnHistoryLongClickListener {


    @BindView(R.id.history_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    LoadImagePresenter mLoadImagePresenter;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        DaggerPresentersComponent.builder()
                .appComponent(getAppComponent())
                .presentersModule(new PresentersModule())
                .build()
                .inject(this);

        mLoadImagePresenter.setView(this);

        mRealm = Realm.getInstance(getContext());

        initToolbar();
        initHistory();
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
        mLoadImagePresenter.getFeaturedContent(query);
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
        if (image != null && image.getDisplaySizes().size() > 0) {
            mRealm.beginTransaction();
            HistoryElement element = mRealm.createObject(HistoryElement.class);
            element.setPhrase(phrase);
            element.setUrl(image.getDisplaySizes().get(0).getUri());
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.HISTORY_DATE_PATTERN, Locale.ENGLISH);
            element.setDate(dateFormat.format(new Date()));
            mRealm.commitTransaction();
        } else showText(getString(R.string.no_results));
    }

    @Override
    public void onHistoryLongClick(HistoryElement element) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_this_history_element)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    mRealm.beginTransaction();
                    RealmResults<HistoryElement> elements = mRealm.where(HistoryElement.class)
                            .equalTo(getString(R.string.realm_id), element.getId())
                            .equalTo(getString(R.string.realm_url), element.getUrl())
                            .equalTo(getString(R.string.realm_date), element.getDate())
                            .equalTo(getString(R.string.realm_phrase), element.getPhrase())
                            .findAll();
                    if (!elements.isEmpty()) {
                        for (int i = elements.size() - 1; i >= 0; i--) {
                            if (elements.get(i).equals(element))
                                elements.get(i).removeFromRealm();
                        }
                    }
                    mRealm.commitTransaction();
                })
                .setNegativeButton(R.string.no, (dialog, id) -> {
                });
        // Create the AlertDialog object and return it
        builder.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRealm.close();
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

    private void initHistory() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new HistoryListAdapter(getContext(),
                mRealm.allObjects(HistoryElement.class), this));
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
