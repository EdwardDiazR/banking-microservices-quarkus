package com.bank.loan;


import com.bank.loan.dto.LoanDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.math.BigDecimal;

@Path("api/v1/loan")
public class LoanController {

    @Inject
    ILoanService loanService;

    @GET
    @Path("")
    public LoanDTO getLoan(){
        return new LoanDTO("333", BigDecimal.valueOf(300));
    }

    @GET
    @Path("detail")
    public String getLoanId(@QueryParam("id") String id){
        return id;
    }

}
