package com.example.demo.model;

import java.math.BigDecimal;

public class Calculator {
    private BigDecimal cash;
    private BigDecimal savings;
    private BigDecimal expensesPerDay;
    private BigDecimal monthlyExpenses;

    // Getters and setters
    public BigDecimal getCash() { return cash; }
    public void setCash(BigDecimal cash) { this.cash = cash; }

    public BigDecimal getSavings() { return savings; }
    public void setSavings(BigDecimal savings) { this.savings = savings; }

    public BigDecimal getExpensesPerDay() { return expensesPerDay; }
    public void setExpensesPerDay(BigDecimal expensesPerDay) { this.expensesPerDay = expensesPerDay; }

    public BigDecimal getMonthlyExpenses() { return monthlyExpenses; }
    public void setMonthlyExpenses(BigDecimal monthlyExpenses) { this.monthlyExpenses = monthlyExpenses; }
}
