package com.example.serhii.gettyimagesviewer.model.entities;

import io.realm.RealmObject;

/**
 * Created by Serhii on 07.02.2018.
 */

public class HistoryElement extends RealmObject {

    private String id;
    private String url;
    private String phrase;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
