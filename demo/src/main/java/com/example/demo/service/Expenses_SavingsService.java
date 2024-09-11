package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Expenses_Savings;
import com.example.demo.repo.Expenses_SavingsRepo;

@Service
public class Expenses_SavingsService {
    @Autowired
    private Expenses_SavingsRepo expensesRepo;

    public List<BigDecimal> getExpenses() {
        return expensesRepo.findAll().stream()
                .map(Expenses_Savings::getExpenses)
                .toList();
    }

    @Autowired
    private Expenses_SavingsRepo savingsRepo;
    
    public List<BigDecimal> getSavings() {
        return savingsRepo.findAll().stream()
                .map(Expenses_Savings::getSavings)
                .toList();
    }
}