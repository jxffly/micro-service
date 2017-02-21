package io.junq.examples.dropwizard.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class HelloDropwizardResource {
	public HelloDropwizardResource() {
		super();
	}
	
	@Timed
	@GET
	@Path("hello")
	@Produces(MediaType.TEXT_PLAIN)
	public String hello() {
		return "Hellow world, Dropwizard rocks.";
	}
}
