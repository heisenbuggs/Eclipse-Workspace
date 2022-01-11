package org.studyeasy.showroom.resources;


import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("/")
public class DemoResource {
	
	@Context
	private UriInfo uriInfo;
	

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String usefulAnnotations(@HeaderParam("HeaderValue") String headerValue, @CookieParam("_xsrf") String cookieValue) {
		return "The server is up and running with header value " + headerValue + " and Cookie value as " + cookieValue;
	}
	
	@GET
	@Path("demo")
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public String contextDemo(@HeaderParam("header") String headerValue) throws Exception {
		
		if (headerValue == null) {
			throw new NotFoundException();
		}
		return "The value of custom header is " + headerValue;
		
	}
}
