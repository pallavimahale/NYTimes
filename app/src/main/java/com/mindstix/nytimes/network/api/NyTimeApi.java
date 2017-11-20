package com.mindstix.nytimes.network.api;


import com.mindstix.nytimes.network.models.TopStoriesResponse;
import com.mindstix.nytimes.network.models.books.BestSellerData;
import com.mindstix.nytimes.network.models.books.BestSellerOverview;
import com.mindstix.nytimes.network.models.books.history.BooksHistoryResponse;
import com.mindstix.nytimes.network.models.search.SearchKeywordResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NyTimeApi {

    @GET("/svc/topstories/v2/home.json")
    Call<TopStoriesResponse> getTopStories(@Query("api-key") String apiKey);

    @GET("/svc/books/v3/lists/overview.json")
    Call<BestSellerOverview> getBooksOverview(@Query("api-key") String apiKey);

    @GET("/svc/books/v3/lists.json")
    Call<BestSellerData> getBestSellers(@Query("api-key") String apiKey, @Query("list") String
            listName);

    @GET("/svc/books/v3/lists/best-sellers/history.json")
    Call<BooksHistoryResponse> getBooksHistory(@Query("api-key") String apiKey, @Query("isbn")
            String listName);

    @GET("/svc/search/v2/articlesearch.json")
    Call<SearchKeywordResult> searchResultByKeyword(@Query("api-key") String apiKey, @Query("q")
            String keyword);

    @GET("/svc/search/v2/articlesearch.json")
    Call<SearchKeywordResult> searchResultByCategory(@Query("api-key") String apiKey, @Query("fq")
            String keyword);
}
