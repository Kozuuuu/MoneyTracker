package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.demo.model.Calculator;

@Service
public class CalculatorService {

    public BigDecimal calculateExtra(Calculator input) {
        BigDecimal totalExpenses = input.getExpensesPerDay().multiply(BigDecimal.valueOf(150))
                                        .add(input.getMonthlyExpenses());
        BigDecimal extra = input.getCash().subtract(input.getSavings()).subtract(totalExpenses);
        return extra;
    }
}
