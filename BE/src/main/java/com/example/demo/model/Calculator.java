package com.example.demo.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Calculator {
    private BigDecimal cash;
    private BigDecimal savings;
    private BigDecimal expensesPerDay;
    private BigDecimal monthlyExpenses;
}
