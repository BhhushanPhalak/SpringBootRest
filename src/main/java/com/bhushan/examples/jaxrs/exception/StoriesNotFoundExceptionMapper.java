package com.bhushan.examples.jaxrs.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.beans.factory.annotation.Value;

@Provider
public class StoriesNotFoundExceptionMapper implements ExceptionMapper<StoriesNotFoundException> {

	@Value("${message.storiesNotFound}")
	String message;
	
	@Override
	public Response toResponse(StoriesNotFoundException exception) {
		return Response.serverError().entity(message).type(MediaType.APPLICATION_JSON).build();
	}

}
