package com.bhushan.examples.jaxrs.service;

import com.bhushan.examples.jaxrs.model.Comments;
import com.bhushan.examples.jaxrs.model.ImportedStories;
import com.bhushan.examples.jaxrs.model.Stories;
import com.bhushan.examples.jaxrs.stories.StoriesApi;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RetrofitService implements APIConfiguration {

    private String accessToken;

    private StoriesApi service;

    private List<Stories> bestStories = new ArrayList<>();
    private List<ImportedStories> importedBestStories = new ArrayList<>();

    public RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(StoriesApi.class);
        this.accessToken = "token " + System.getenv("ACCESS_TOKEN");

        Timer t = new java.util.Timer();
        t.schedule(
            new java.util.TimerTask() {
                @Override
                public void run() {
                    System.out.println("Thread Start");
                    try {
                        createTopStoriesData();
                        System.out.println("Thread Start");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //t.cancel();
                }
            },
            0,900000
        );
    }

    /*
     *   Ge Best Stories List from hacker-news
     * */
    public List<Stories> getTopLimitedStoriesData(Integer noOfStories) throws IOException {
        Collections.sort(bestStories);
        return (bestStories.size() > 0) ? bestStories.subList(0, noOfStories-1) : bestStories;
    }

    /*
     *   Ge Best Stories List from hacker-news
     * */
    public List<Stories> getAllBestStoriesData() throws IOException {
        return bestStories;
    }

    /*
     *   Ge Comments on story from hacker-news
     * */
    public List<Comments> getCommentsOnStory(Integer storyId, Integer noOfComments) throws IOException {

        ImportedStories story = getStory(storyId);

        List<Comments> listOfComments = story.getKids().parallelStream().map(kid -> convertToComments(story, getStory(kid))).collect(Collectors.toList());
        Collections.sort(listOfComments);

        return listOfComments;
    }


    /*
     *   Create best stories data list from hacker-news
     * */
    public void createTopStoriesData() throws IOException {

        final List<Integer> topStories = getBestStories();

        System.out.println("topStories Size :" + topStories.size());
        bestStories = topStories.parallelStream().map(storyId -> convertToStories(getStory(storyId))).collect(Collectors.toList());
        System.out.println("bestStories Size :" + bestStories.size());
    }

    /*
    *   Ge Best Stories List from hacker-news
    * */
    public List<Integer> getBestStories() throws IOException {
        Call<List<Integer>> retrofitCall = service.getBestStories();

        Response<List<Integer>> response = retrofitCall.execute();

        if (!response.isSuccessful()) {
            System.out.println(response.errorBody().string());
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }

        return response.body();
    }

    /*
     *   Ge Stories details from hacker-news
     * */
    public ImportedStories getStory(Integer storyId) {
        Call<ImportedStories> retrofitCall = service.getStoriesById(storyId);

        Response<ImportedStories> response = null;
        try {
            response = retrofitCall.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!response.isSuccessful()) {
            System.out.println(response.errorBody());
        }
        return response.body();
    }

    private Stories convertToStories(ImportedStories importedStories){
        Stories stories = new Stories();
        stories.setUser(importedStories.getBy());
        stories.setUrl(importedStories.getUrl());
        stories.setScore(importedStories.getScore());
        stories.setTimeOfSubmission(new Date(importedStories.getTime()));
        stories.setTitle(importedStories.getTitle());

        return stories;
    }

    private Comments convertToComments(ImportedStories story, ImportedStories comment){
        Comments comments = new Comments();

        comments.setUser(comment.getBy());
        comments.setText(comment.getText());
        comments.setDescendants(story.getDescendants());
        comments.setNoOfChildComment(comment.getKids() != null ? comment.getKids().size() : 0);

        return comments;
    }
}