package com.example.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SavingsResponse;
import com.example.demo.model.Expenses_Savings;
import com.example.demo.service.Expenses_SavingsService;

@RestController
@RequestMapping
public class Expenses_SavingsController {

    @Autowired
    private Expenses_SavingsService expenses_SavingsService;

    @GetMapping("/alldetails")
    public List<Expenses_Savings> getAllDetails() {
        return expenses_SavingsService.getAllDetails();
    }
  
    @GetMapping("/savings")
    public List<SavingsResponse> getAllSavings() {
        return expenses_SavingsService.getSavings();
    }

    @GetMapping("/expenses")
    public List<BigDecimal> getExpenses() {
        return expenses_SavingsService.getExpenses();
    }

    
    @PutMapping("/update")
    public ResponseEntity<Expenses_Savings> updateExpensesSavings(@RequestBody Expenses_Savings expSave) {
        Expenses_Savings updatedRecord = expenses_SavingsService.updateExpenses_Savings(expSave);
        if (updatedRecord != null) {
            return ResponseEntity.ok(updatedRecord);
        } else {
            // Return 404 Not Found if the record does not exist
            return ResponseEntity.notFound().build();
        }
    }
}