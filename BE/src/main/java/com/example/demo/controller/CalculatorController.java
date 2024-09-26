package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CalculationResponse;
import com.example.demo.model.Calculator;
import com.example.demo.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping // ("/calculate")
    public CalculationResponse calculateExtra(@RequestBody Calculator input) {
        return calculatorService.calculateExtra(input);
    }
}
