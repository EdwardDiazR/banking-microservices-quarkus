package com.bank.loan;

import com.bank.CustomerClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/loan")
public class LoanResource {

    @RestClient
    CustomerClient customerClient;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        return customerClient.greet();

    }
}
