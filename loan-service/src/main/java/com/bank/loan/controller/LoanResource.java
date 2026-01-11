package com.bank.loan.controller;

import com.bank.CustomerClient;
import com.bank.loan.ILoanService;
import com.bank.loan.dto.LoanDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("api/v1/loan")
@RolesAllowed("")
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
    @Path("{loanNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public LoanDTO getLoanByLoanNumber() {
        return _loanService.getLoan();
    }

    @PATCH
    @Path("change-payment-date")
    public Response changePaymentDate(){
        return Response.ok("Date changed successfully").build();
    }

}
