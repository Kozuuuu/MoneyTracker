package com.example.demo.service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import com.example.demo.model.CalculationResponse;
import com.example.demo.model.Calculator;

@Service
public class CalculatorService {

    public CalculationResponse calculateExtra(Calculator input) {
        LocalDate today = LocalDate.now();
        LocalDate nextPayrollDate = calculateNextPayrollDate(today);
        long daysUntilNextPayroll = ChronoUnit.DAYS.between(today, nextPayrollDate);
        
        // Calculate additional expenses for the remaining days
        BigDecimal additionalExpenses = input.getExpensesPerDay()
            .multiply(BigDecimal.valueOf(daysUntilNextPayroll));
        
        // Calculate total expenses by adding monthly expenses
        BigDecimal totalExpenses = additionalExpenses
            .add(input.getMonthlyExpenses());
        
        // Calculate extra amount
        BigDecimal extra = input.getCash()
            .subtract(input.getSavings())
            .subtract(totalExpenses);

        // Return a new CalculationResponse object with all the required values
        return new CalculationResponse(nextPayrollDate, daysUntilNextPayroll, totalExpenses, extra);
    }

    private LocalDate calculateNextPayrollDate(LocalDate today) {
        LocalDate nextPayrollDate = today.withDayOfMonth(15);
        if (today.getDayOfMonth() > 15) {
            nextPayrollDate = today.withDayOfMonth(today.lengthOfMonth());
        }
        
        if (nextPayrollDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            nextPayrollDate = nextPayrollDate.minusDays(1);
        } else if (nextPayrollDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            nextPayrollDate = nextPayrollDate.minusDays(2);
        }

        return nextPayrollDate;
    }
}
