package com.example.demo.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "expenses_savings")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expenses_Savings {

    @Id
    private Integer Id;
    
    @Column(name = "expenses")
    private BigDecimal expenses;

    @Column(name = "expenses_description")
    private String expensesDescription;

    @Column(name = "savings")
    private BigDecimal savings;

    @Column(name = "monthly_payments")
    private BigDecimal monthlyPayments;

    @Column(name = "savings_description")
    private String savingsDescription;

    @Column(name = "total_savings")
    private BigDecimal totalSavings;
}