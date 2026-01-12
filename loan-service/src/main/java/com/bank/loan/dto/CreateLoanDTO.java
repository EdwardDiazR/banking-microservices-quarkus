package com.bank.loan.dto;

import com.bank.loan.constants.AccountSignType;
import com.bank.loan.constants.LoanInterestPeriod;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public record CreateLoanDTO(BigDecimal amount,
                            BigDecimal interestRate,
                            int termInMonths,
                            LoanInterestPeriod interestPeriodFrequency,
                            String type,
                            String currency,
                            AccountSignType signType,
                            LocalDate firstPaymentDate,Long financialProductId) {
}
