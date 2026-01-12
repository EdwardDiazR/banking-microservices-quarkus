package com.bank.loan;

import com.bank.loan.dto.CreateLoanDTO;
import com.bank.loan.dto.LoanDTO;
import com.bank.loan.dto.LoanDashboardDTO;

import java.util.Optional;

public interface ILoanService {

    Loan createLoan(CreateLoanDTO loanDTO);
    LoanDTO getLoan();
    Loan getLoanById(Long id);
    Loan getLoanByProductId(Long productId);
    LoanDashboardDTO getLoanByIdForApp(Long id);
}
