package com.bhushan.examples.jaxrs.stories;

import com.bhushan.examples.jaxrs.model.ImportedStories;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface StoriesApi {

    @GET("beststories.json")
    Call<List<Integer>> getBestStories();

    @GET("item/{id}.json")
    Call<ImportedStories> getStoriesById(@Path("id") Integer id);
}
