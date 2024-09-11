package com.example.demo.dto;

import java.math.BigDecimal;

public class SavingsResponse {
    private BigDecimal savings;
    private String savingsDescription;

    // Constructor, getters, and setters
    public SavingsResponse(BigDecimal savings, String savingsDescription) {
        this.savings = savings;
        this.savingsDescription = savingsDescription;
    }

    public BigDecimal getSavings() { return savings; }
    public void setSavings(BigDecimal savings) { this.savings = savings; }

    public String getSavingsDescription() { return savingsDescription; }
    public void setSavingsDescription(String savingsDescription) { this.savingsDescription = savingsDescription; }
}
    
