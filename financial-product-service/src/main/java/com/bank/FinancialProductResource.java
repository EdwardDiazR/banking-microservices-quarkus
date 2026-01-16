package com.bank;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/api/v1/financial-product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FinancialProductResource {

    @RestClient
    LoanClient loanClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }

    @GET
    @Path("{productId}")
    public Response getByProductId(@PathParam("productId") Long productId) {

        try {
            String data = loanClient
                    .getLoanByFinancialProductId(productId)
                    .readEntity(String.class);
            return Response.ok(data).build();

        } catch (WebApplicationException e) {

            int status = e.getResponse().getStatus();
            String body = e.getResponse().readEntity(String.class);

            return Response
                    .status(status)
                    .entity(body)
                    .build();
        }
    }
}
