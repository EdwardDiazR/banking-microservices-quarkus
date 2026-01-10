package com.bank.loan;

import com.bank.loan.dto.CreateLoanDTO;
import com.bank.loan.dto.LoanDTO;

public interface ILoanService {

    void createLoan(CreateLoanDTO loanDTO);
    LoanDTO getLoan();
}
