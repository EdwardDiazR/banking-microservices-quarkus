package com.bank.loan.dto;

import java.math.BigDecimal;

public record LoanDTO(String loanNumber, BigDecimal amount) {
}
