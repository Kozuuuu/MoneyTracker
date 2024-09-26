package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.FinancialRec;
import com.example.demo.service.FinancialRecService;

@RestController
@RequestMapping("/records")
public class FinancialRecController {

    @Autowired
    private FinancialRecService financialRecService;

    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping
    public List<FinancialRec> getAllRecords() {
        return financialRecService.getAllRecords();
    }
    
    @PostMapping
    public ResponseEntity<FinancialRec> addRecord(@RequestBody FinancialRec financialRec) {
        FinancialRec createdRecord = financialRecService.addRecord(financialRec);
        return ResponseEntity.ok(createdRecord);
    }
    
    @GetMapping("/{payroll}")
    public ResponseEntity<FinancialRec> getRecordById(@PathVariable Long payroll) {
        return financialRecService.getRecordById(payroll)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{payroll}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long payroll) {
        financialRecService.deleteRecord(payroll);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{payroll}")
    public ResponseEntity<FinancialRec> updateRecord(@PathVariable Long payroll, @RequestBody FinancialRec updatedRecord) {
        FinancialRec record = financialRecService.updateRecord(payroll, updatedRecord);
        if (record != null) {
            return ResponseEntity.ok(record);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
