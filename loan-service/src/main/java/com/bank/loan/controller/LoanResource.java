package com.bank.loan.controller;

import com.bank.CustomerClient;
import com.bank.loan.ILoanService;
import com.bank.loan.Loan;
import com.bank.loan.dto.CreateLoanDTO;
import com.bank.loan.dto.LoanDTO;
import com.bank.loan.dto.LoanDashboardDTO;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("api/v1/loan")
@Produces(MediaType.APPLICATION_JSON)
//@RolesAllowed({"CRM"})
public class LoanResource {

    @Inject
    ILoanService _loanService;

    @Inject
    SecurityIdentity identity;

    @Inject
    JsonWebToken jwt;

    public void debug() {
        System.out.println(identity.isAnonymous()); // false
        System.out.println(identity.getRoles());     // [CUSTOMER]
    }

    @GET
    @Path("/")
    public Response getLoans(){
        return Response.ok().build();
    }

    @GET
    @Path("/{loanId}")
    public Response getLoanById(@PathParam("loanId") Long loanId) {
        try {
            Loan loan = _loanService.getLoanById(loanId);
            return Response.ok(loan).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @GET
    @Path("ib/{loanId}")
    public Response getLoanByIdForIB(@PathParam("loanId") Long loanId) {
        try {
            LoanDashboardDTO loan = _loanService.getLoanByIdForApp(loanId);
            return Response.ok(loan).build();
        } catch (Exception e) {
            return Response.status(404, e.getMessage()).build();
        }
    }

    @POST
    @Path("/")
    public Response createLoan(CreateLoanDTO createLoanDTO) {
        Loan loan = _loanService.createLoan(createLoanDTO);
        return Response.ok(loan).build();
    }

    @GET
    @Path("/by-financial-product/{productId}")
    public Response getLoanByFinancialProductId() {
        return Response.ok().build();
    }

    @GET
    @Path("/by-financial-products")
    public Response getLoansByFinancialProducts(Long[] productsId) {
        return Response.ok().build();
    }

    @GET
    @Path("/{loanId}/amortization")
    public Response getLoanAmortizationTableByLoanId() {
        return Response.ok().build();
    }

    @PATCH
    @Path("/{loanId}/change-payment-date")
    public Response changePaymentDate() {
        return Response.ok("Date changed successfully").build();
    }
}
