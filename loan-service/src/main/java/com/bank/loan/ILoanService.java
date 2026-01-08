package com.bank.loan;

import com.bank.loan.dto.LoanDTO;

public interface ILoanService {

    void createLoan();
    LoanDTO getLoan();
}
