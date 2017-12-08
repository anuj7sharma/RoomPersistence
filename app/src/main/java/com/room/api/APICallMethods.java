package com.room.api;

import com.room.model.GetSearchResultResponse;
import com.room.utility.Constants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public interface APICallMethods {

    @GET(Constants.IMAGE_SEARCH_API)
    Single<GetSearchResultResponse> getSearchResult(@Query("count") int count, @Query("offset") int page, @Query("q") String query);
}
