package com.bank.loan.service;

import com.bank.loan.ILoanService;
import com.bank.loan.dto.LoanDTO;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@ApplicationScoped
public class LoanServiceImpl implements ILoanService {

    public void getLoanById(){}
    public void getLoanByFinancialProductPublicId(){}
    public void getLoanByFinancialProductNumber(){}

    @Override
    public void createLoan(){};

    @Override
    public LoanDTO getLoan(){
        return new LoanDTO("100120", BigDecimal.valueOf(250000));
    }


}
