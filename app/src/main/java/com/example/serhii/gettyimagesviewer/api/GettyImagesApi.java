package com.example.serhii.gettyimagesviewer.api;

import com.example.serhii.gettyimagesviewer.model.entities.GettyImagesResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Serhii on 06.02.2018.
 */

public interface GettyImagesApi {

    @GET("/search/images?ﬁelds=id,title,thumb&sort_order=best")
    Observable<GettyImagesResponse> getBestContent(@HeaderMap Map<String, String> headers,
                                                   @Query("phrase") String phrase);

}
