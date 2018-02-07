package com.example.serhii.gettyimagesviewer.api;

import com.example.serhii.gettyimagesviewer.model.entities.GettyImagesResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Serhii on 06.02.2018.
 */

public interface GettyImagesApi {
    @Headers({
            "Api-Key: qjru52ej5mc9rjffvm2rnftd"
    })
    @GET("/v3/search/images?ﬁelds=id,title,thumb&sort_order=best")
    Observable<GettyImagesResponse> getBestContent(@Query("phrase") String phrase);

}
