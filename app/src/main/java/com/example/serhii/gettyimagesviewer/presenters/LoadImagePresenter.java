package com.example.serhii.gettyimagesviewer.presenters;

import com.example.serhii.gettyimagesviewer.model.ILoadImageDataSource;
import com.example.serhii.gettyimagesviewer.model.entities.GettyImagesResponse;
import com.example.serhii.gettyimagesviewer.model.entities.Image;
import com.example.serhii.gettyimagesviewer.utils.rx.RxErrorAction;
import com.example.serhii.gettyimagesviewer.utils.rx.RxRetryWithDelay;
import com.example.serhii.gettyimagesviewer.views.LoadImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Serhii on 06.02.2018.
 */

public class LoadImagePresenter extends BasePresenter<LoadImageView> {
    private final ILoadImageDataSource loadImageDataSource;

    public LoadImagePresenter(ILoadImageDataSource loadImageDataSource) {
        this.loadImageDataSource = loadImageDataSource;
    }

    public void getFeaturedContent(String phrase) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Api-Key", "qjru52ej5mc9rjffvm2rnftd");
        addSubscription(loadImageDataSource.getBestContent(headers, phrase)
                .retryWhen(new RxRetryWithDelay())
                .map(GettyImagesResponse::getImages)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(images -> {
                    if (images.size() > 0) getView().addHistory(images.get(0), phrase);
                    else getView().addHistory(null, phrase);
                }, new RxErrorAction(getView().getContext()))
        );
    }
}
