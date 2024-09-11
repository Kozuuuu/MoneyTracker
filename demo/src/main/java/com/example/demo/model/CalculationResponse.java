package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Generates getters, setters, toString, equals, and hashCode
@AllArgsConstructor // Generates a constructor with all fields
@NoArgsConstructor  // Generates a no-args constructor
public class CalculationResponse {
    private LocalDate nextPayrollDate;
    private long daysUntilNextPayroll;
    private BigDecimal totalExpenses;
    private BigDecimal extra;
}
