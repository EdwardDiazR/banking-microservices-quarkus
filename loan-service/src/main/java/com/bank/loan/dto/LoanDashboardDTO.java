package com.bank.loan.dto;

import java.math.BigDecimal;

public record LoanDashboardDTO(String loanNumber,
                               BigDecimal balance,
                               String type) {
}
