package com.bank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/customer")
public class CustomerResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String greet() {
        return "Hello Customer from Microservice customer";
    }
}
