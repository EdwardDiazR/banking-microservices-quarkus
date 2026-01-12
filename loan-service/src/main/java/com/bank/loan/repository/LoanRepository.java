package com.bank.loan.repository;

import com.bank.loan.Loan;

import java.util.Optional;

public interface LoanRepository {

    void saveLoan(Loan loan);
    Optional<Loan> getById(Long id);
    Optional<Loan> getByProductId(Long productId);

}
