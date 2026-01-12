package com.bank;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8082")
@Path("api/v1/loan")
public interface LoanClient {

    @GET
    @Path("/by-financial-product/{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getLoanByFinancialProductId(@PathParam("productId") Long productId);
}