package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SavingsResponse;
import com.example.demo.model.Expenses_Savings;
import com.example.demo.repo.Expenses_SavingsRepo;

@Service
public class Expenses_SavingsService {

    @Autowired Expenses_SavingsRepo expenses_SavingsRepo;

    public List<Expenses_Savings> getAllDetails() {
        return expenses_SavingsRepo.findAll();
    }

    @Autowired
    private Expenses_SavingsRepo expensesRepo;

    public List<BigDecimal> getExpenses() {
        return expensesRepo.findAll().stream()
                .map(Expenses_Savings::getExpenses)
                .toList();
    }

    public List<BigDecimal> postSavings() {
        return savingsRepo.findAll().stream()
                .map(Expenses_Savings::getSavings)
                .toList();
    }

    @Autowired
    private Expenses_SavingsRepo savingsRepo;

    public List<SavingsResponse> getSavings() {
        return savingsRepo.findAll().stream()
                .map(exp -> new SavingsResponse(exp.getSavings(), exp.getSavingsDescription()))
                .collect(Collectors.toList());
            
    }


    // public void updateExpenses(BigDecimal expenses, String expenses_description) {
    //     expenses_savingsRepo.updateExpensesDetails(expenses, expenses_description);
    // }

    // public List<Object[]> getExpensesDetails() {
    //     return expenses_savingsRepo.getExpensesDetails();
    // }

    // public void updateSavings(BigDecimal savings, String savings_description) {
    //     expenses_savingsRepo.updateSavingsDetails(savings, savings_description);
    // }

    // public List<Object[]> getSavingsDetails() {
    //     return expenses_savingsRepo.getSavingsDetails();
    // }
}