package org.studyeasy.showroom.exceptions;

import org.studyeasy.showroom.model.ErrorPayload;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {


	@Override
	public Response toResponse(NotFoundException exception) {
		ErrorPayload error = new ErrorPayload(404, "Element Not Found");
		
		return Response.status(Status.NOT_FOUND).entity(error).build();
	}
}
