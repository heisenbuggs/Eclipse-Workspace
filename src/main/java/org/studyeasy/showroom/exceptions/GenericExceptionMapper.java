package org.studyeasy.showroom.exceptions;

import org.studyeasy.showroom.model.ErrorPayload;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		ErrorPayload error = new ErrorPayload(500, "Internal server error!!");
		
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
	}

	
}
