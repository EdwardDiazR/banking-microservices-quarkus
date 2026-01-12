package com.bank.loan.service;

import com.bank.loan.ILoanService;
import com.bank.loan.Loan;
import com.bank.loan.constants.LoanInterestPeriod;
import com.bank.loan.constants.LoanStatus;
import com.bank.loan.constants.PaymentFrequency;
import com.bank.loan.dto.CreateLoanDTO;
import com.bank.loan.dto.LoanDTO;
import com.bank.loan.dto.LoanDashboardDTO;
import com.bank.loan.repository.LoanRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class LoanServiceImpl implements ILoanService {

    private static final BigDecimal LATE_FEE_RATE = new BigDecimal(6)
            .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);

    @Inject
    LoanRepository loanRepository;

    @Override
    public LoanDTO getLoan() {
        return new LoanDTO("100120", BigDecimal.valueOf(250000));
    }

    @Override
    public LoanDashboardDTO getLoanByIdForApp(Long id){
        Loan loan = getLoanById(id);
        return new LoanDashboardDTO(loan.getId().toString(),loan.getOutstandingPrincipalAmount().add(loan.getInterestBalance()),"LOAN");
    }

    public Set<Loan> getLoans(){
        return new HashSet<>();
    }

    @Override
    public Loan getLoanById(Long id){
        return loanRepository.getById(id).orElseThrow(()-> new NotFoundException("Prestamo no encontrado"));
    }

    public void getLoanByProductId(Long productId) {}

    @Override
    @Transactional
    public Loan createLoan(CreateLoanDTO loanDTO) {
        final LocalDateTime now = LocalDateTime.now();

        BigDecimal disbursementAmount = loanDTO.amount()
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal interestRate = loanDTO.interestRate()
                .divide(BigDecimal.valueOf(100), 10, RoundingMode.UNNECESSARY);

        BigDecimal installmentAmount = calculateInstallment(disbursementAmount, interestRate, loanDTO.termInMonths())
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal projectedInterest = calculateProjectedInterest(loanDTO.amount(), interestRate, loanDTO.termInMonths(), loanDTO.interestPeriodFrequency());

        BigDecimal dailyInterestFactor = calculateDailyInterestFactor(disbursementAmount, interestRate, loanDTO.termInMonths())
                .setScale(2, RoundingMode.HALF_UP);

        LocalDate loanDueDate = now.toLocalDate()
                .plusMonths(loanDTO.termInMonths());


//        FinancialProduct fp = _financialProductService.createFinancialProduct(
//                new CreateFinancialProductDTO("LOAN", loanDTO.relatives(), loanDTO.signType())
//        );

        Loan loan = Loan.builder()
              .financialProductId(loanDTO.financialProductId())
                .status(LoanStatus.CREATED)
                .type(loanDTO.type())
                .currency(loanDTO.currency())//In future can validate by loan type
                .principalAmount(disbursementAmount)
                .availableAmountForDisbursement(BigDecimal.ZERO)
                .outstandingPrincipalAmount(disbursementAmount)
                .interestBalance(BigDecimal.ZERO)
                .interestRate(interestRate)
                .termInMonths(loanDTO.termInMonths())
                .paymentFrequency(PaymentFrequency.MONTHLY) //todo: get payment frequency in dto, ej: Monthly, Weekly, Trimestral
                .dailyInterestFactor(dailyInterestFactor)
                .installmentAmount(installmentAmount)
                .lateFeeRate(LATE_FEE_RATE)
                .lateFeeBalance(new BigDecimal(0))
                .totalInstallmentBalance(installmentAmount)
                .totalPaidInterest(BigDecimal.ZERO)
                .projectedInterest(projectedInterest.setScale(2, RoundingMode.HALF_UP))
                .oneCycleTimes(0)
                .twoCycleTimes(0)
                .paymentsMade(0)
                .paymentsPending(loanDTO.termInMonths())
                .firstPaymentDate(loanDTO.firstPaymentDate())
                .nextPaymentDate(loanDTO.firstPaymentDate()) //Check
                .lastPaymentDate(null)
                .lastInterestBalanceUpdateDate(null)
                .disbursementDate(null)
                .lastInterestRateReviewDate(now)
                .dueDate(loanDueDate)
                .updatedAt(null)
                .linkedAccount(null)
                .canAutoDebit(false)
                .isLineOfCredit(false)
                .isDeleted(false)
                .build();

        loanRepository.saveLoan(loan);

        return loan;
    }

    public BigDecimal calculateInstallment(BigDecimal capital,
                                     BigDecimal tasa,
                                     int meses
    ) {

        BigDecimal monthlyRate = tasa.divide(BigDecimal.valueOf(12), 10, RoundingMode.HALF_UP);

        // (1 + r)^n
        BigDecimal onePlusR = BigDecimal.ONE.add(monthlyRate);
        BigDecimal factor = onePlusR.pow(meses);

        // r * (1 + r)^n
        BigDecimal numerator = monthlyRate.multiply(factor);

        // (1 + r)^n - 1
        BigDecimal denominator = factor.subtract(BigDecimal.ONE);

        // Cuota = P * (numerator / denominator)
        BigDecimal cuota = capital.multiply(numerator)
                .divide(denominator, 2, RoundingMode.HALF_UP);

        return cuota.setScale(2, RoundingMode.HALF_UP);


    }

    public BigDecimal calculateDailyInterestFactor(BigDecimal amount,
                                                   BigDecimal interestRate,
                                                   int term
    ) {
        //TODO: in future, validate to calculate yearly or monthly depending of the loan
        int YEAR_BASE_DAYS = 360;
        BigDecimal dailyFactorInPercentage = interestRate
                .divide(BigDecimal.valueOf(YEAR_BASE_DAYS), 10, RoundingMode.HALF_UP);

        return amount
                .multiply(dailyFactorInPercentage)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private int getInterestPeriodFrequencyInNumber(LoanInterestPeriod interestPeriodFrequency) {
        switch (interestPeriodFrequency) {
            case MONTHLY -> {
                return 1;
            }
            case YEARLY -> {
                return 12;
            }
            default -> {
                throw new IllegalArgumentException("Debe elegir una frecuencia de calculo de intereses: Anual o Mensual");
            }
        }
    }


    public BigDecimal calculateProjectedInterest(BigDecimal amount,
                                                 BigDecimal interestRate,
                                                 int term,
                                                 LoanInterestPeriod period
    ) {


        var termInYears = BigDecimal.valueOf(term / getInterestPeriodFrequencyInNumber(period));
        var i = amount
                .multiply(interestRate)
                .multiply(termInYears);

        return i;


    }

    public void changePaymentDate(Long loanId, LocalDate newPaymentDate) {
    }

    public void updateInterestRate(){}
}
