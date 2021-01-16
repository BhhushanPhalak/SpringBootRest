package com.bhushan.examples.jaxrs.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/stories")
@Produces(MediaType.APPLICATION_JSON)
public interface StoriesService {

	@GET
	@Path("/test/{id}")
	public Response getStoryById(@PathParam("id") Integer storyId) throws IOException;

	@GET
	@Path("/bestStories")
	public Response getBestStories(
		@DefaultValue("10") @QueryParam("noOfStories") Integer noOfStories
	) throws IOException;

	@GET
	@Path("/pastStories")
	public Response getAllPastStories() throws IOException;

	@GET
	@Path("/comments/{id}")
	public Response getComments(
			@PathParam("id") Integer storyId,
			@DefaultValue("10") @QueryParam("noOfComments") Integer noOfComments
	) throws IOException;

}
