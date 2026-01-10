package com.bank.loan;

import com.bank.loan.constants.LoanStatus;
import com.bank.loan.constants.PaymentFrequency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Loan {

    // =========================================================
    // IDENTIFICACIÓN GENERAL
    // =========================================================

    public Long id;
//    private FinancialProduct financialProduct;
    private LoanStatus status;
    private String type;
    private String currency;

    // =========================================================
    // MONTOS PRINCIPALES Y BALANCES
    // =========================================================

    /** Monto desembolsado originalmente */
    private BigDecimal principalAmount;

    /** Monto disponible para futuros desembolsos (líneas de crédito) */
    private BigDecimal availableAmountForDisbursement;

    /** Balance de capital pendiente */
    private BigDecimal outstandingPrincipalAmount;

    /** Balance de intereses pendientes */
    private BigDecimal interestBalance;

    /** Balance de mora acumulada */
    private BigDecimal lateFeeBalance;

    /** Balance total de la cuota (capital + interés + mora) */
    private BigDecimal totalInstallmentBalance;

    // =========================================================
    // CONDICIONES FINANCIERAS DEL PRÉSTAMO
    // =========================================================

    /** Tasa de interés */
    private BigDecimal interestRate;

    /** Plazo del préstamo en meses */
    private int termInMonths;

    /** Frecuencia de pago */
    private PaymentFrequency paymentFrequency;

    /** Factor diario de interés */
    private BigDecimal dailyInterestFactor;

    /** Monto de la cuota */
    private BigDecimal installmentAmount;

    /** Tasa de mora */
    private BigDecimal lateFeeRate;

    // =========================================================
    // INTERESES Y CÁLCULOS ACUMULADOS
    // =========================================================

    /** Intereses pagados hasta la fecha */
    private BigDecimal totalPaidInterest;

    /** Intereses proyectados del préstamo */
    private BigDecimal projectedInterest;

    // =========================================================
    // CICLOS Y SEGUIMIENTO DE PAGOS
    // =========================================================

    private int oneCycleTimes;
    private int twoCycleTimes;

    private Integer paymentsMade;
    private Integer paymentsPending;

    // =========================================================
    // FECHAS IMPORTANTES DEL PRÉSTAMO
    // =========================================================

    private LocalDate firstPaymentDate;
    private LocalDate nextPaymentDate;
    private LocalDateTime lastPaymentDate;

    private LocalDateTime lastInterestBalanceUpdateDate;
    private LocalDateTime disbursementDate;

    private LocalDateTime lastInterestRateReviewDate;
    private LocalDate dueDate;

    private LocalDateTime updatedAt;

    // =========================================================
    // CONFIGURACIÓN Y VINCULACIONES
    // =========================================================

    /** Cuenta vinculada para pagos */
    private Long linkedAccount;

    /** Indica si permite débito automático */
    private Boolean canAutoDebit;

    /** Indica si es una línea de crédito */
    private Boolean isLineOfCredit;

    /** Borrado lógico */
    private Boolean isDeleted;

    // =========================================================
    // RELACIONES FUNCIONALES
    // =========================================================

  /*  private AmortizationTable amortizationTable;
    private List<LoanPayment> payments;*/


}
