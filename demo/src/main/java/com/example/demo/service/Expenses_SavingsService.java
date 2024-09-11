package com.example.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SavingsResponse;
import com.example.demo.model.Expenses_Savings;
import com.example.demo.repo.Expenses_SavingsRepo;

@Service
public class Expenses_SavingsService {

    @Autowired
    private Expenses_SavingsRepo expenses_SavingsRepo;

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

    public Expenses_Savings updateExpenses_Savings(Expenses_Savings expSave) {
        Optional<Expenses_Savings> existingRecord = expenses_SavingsRepo.findTopByOrderByIdDesc();

        if (existingRecord.isPresent()) {
            Expenses_Savings record = existingRecord.get();
            record.setExpenses(expSave.getExpenses());
            record.setExpensesDescription(expSave.getExpensesDescription());
            record.setSavings(expSave.getSavings());
            record.setSavingsDescription(expSave.getSavingsDescription());
            record.setMonthlyPayments(expSave.getMonthlyPayments());

            return expenses_SavingsRepo.save(record);
        }
        return null;
    }

    @Autowired
    private Expenses_SavingsRepo savingsRepo;

    public List<SavingsResponse> getSavings() {
        return savingsRepo.findAll().stream()
                .map(exp -> new SavingsResponse(exp.getSavings(), exp.getSavingsDescription()))
                .collect(Collectors.toList());
    }
}