package com.example.demo.model;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "financial_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialRec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payroll;

    @Column(name = "period")
    private String period;

    @Column(name = "date")
    private Date date;

    @Column(name ="cash")
    private BigDecimal cash;

    @Column(name ="savings")
    private BigDecimal savings;

    @Column(name ="monthly_payments")
    private BigDecimal monthlyPayments;

    @Column(name = "expenses")
    private BigDecimal expenses;

    @Column(name ="extra")
    private BigDecimal extra;

    @Column(name = "days_until_next_payroll")
    private Integer daysUntilNextPayroll;

    @Column(name = "next_payroll_date")
    private Date nextPayrollDate;
}
