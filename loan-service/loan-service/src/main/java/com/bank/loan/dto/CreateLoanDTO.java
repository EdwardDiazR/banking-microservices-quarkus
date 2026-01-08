package com.bank.loan.dto;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record CreateLoanDTO(BigDecimal amount,
                            BigDecimal interestRate,
                            int termInMonths,
                            LoanInterestPeriod interestPeriodFrequency,
                            Set<CreateAccountRelativeDTO> relatives,
                            String type,
                            String currency,
                            AccountSignType signType,
                            LocalDate firstPaymentDate) {
}
