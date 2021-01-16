package com.bhushan.examples.jaxrs.service;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.core.Response;

import com.bhushan.examples.jaxrs.exception.StoriesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class StoriesServiceImpl implements StoriesService {

	@Autowired
	RetrofitService retrofitService;

	@Override
	public Response getStoryById(Integer id) throws IOException {
		System.out.println("getStoryById");
		return Response.ok(retrofitService.getStory(id)).build();
	}

	@Override
	public Response getBestStories(Integer noOfStories) throws IOException {

		System.out.println("getBestStories");
		return Response.ok(retrofitService.getTopLimitedStoriesData(noOfStories)).build();
	}

	@Override
	public Response getAllPastStories() throws IOException {

		System.out.println("getAllPastStories");
		return Response.ok(retrofitService.getAllBestStoriesData()).build();
	}

	@Override
	public Response getComments(Integer storyId, Integer noOfComments) throws IOException {

		System.out.println("getComments");
		return Response.ok(retrofitService.getCommentsOnStory(storyId, noOfComments)).build();
	}
}
