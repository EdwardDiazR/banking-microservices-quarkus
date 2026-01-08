package com.bank.loan;

import com.bank.CustomerClient;
import com.bank.loan.dto.LoanDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/loan")
public class LoanResource {

    @RestClient
    CustomerClient customerClient;

    @Inject
    ILoanService _loanService;

    public LoanResource() {
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return customerClient.greet();
    }

    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    public LoanDTO createLoan() {
        return _loanService.getLoan();
    }
}
