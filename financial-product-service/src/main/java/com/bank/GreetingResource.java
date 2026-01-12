package com.bank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/v1/financial-product")
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {
@RestClient
LoanClient loanClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("{productId}")
    public Response getByProductId(@PathParam("productId") Long productId){
        var data= loanClient.getLoanByFinancialProductId(productId).readEntity(String.class);
        return Response.ok(data).build();
    }
}
