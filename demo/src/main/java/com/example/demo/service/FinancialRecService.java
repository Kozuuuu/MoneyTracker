package com.example.demo.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.FinancialRec;
import com.example.demo.repo.FinancialRecRepo;

@Service
public class FinancialRecService {

    @Autowired
    private FinancialRecRepo financialRecRepo;

    public List<FinancialRec> getAllRecords() {
        return financialRecRepo.findAll();
    }

    public Optional<FinancialRec> getRecordById(Long payroll) {
        return financialRecRepo.findById(payroll);
    }

    public void deleteRecord(Long payroll) {
        financialRecRepo.deleteById(payroll);
    }

    public FinancialRec updateRecord(Long payroll, FinancialRec updatedRecord) {
        Optional<FinancialRec> existingRecord = financialRecRepo.findById(payroll);
        if (existingRecord.isPresent()) {
            FinancialRec record = existingRecord.get();
            record.setCash(updatedRecord.getCash());
            record.setDate(updatedRecord.getDate());
            record.setSavings(updatedRecord.getSavings());
            record.setExtra(updatedRecord.getExtra());
            record.setPeriod(updatedRecord.getPeriod());
            return financialRecRepo.save(record);
        }
        return null;
    }

    public FinancialRec addRecord(FinancialRec financialRec) {
        LocalDate today = LocalDate.now();
        LocalDate nextPayrollDate = calculateNextPayrollDate(today);
        long daysUntilNextPayroll = ChronoUnit.DAYS.between(today, nextPayrollDate);

        BigDecimal expenses = BigDecimal.valueOf(daysUntilNextPayroll * 150).add(BigDecimal.valueOf(500));
        BigDecimal extra = financialRec.getCash().subtract(expenses).subtract(financialRec.getSavings());

        financialRec.setDate(Date.valueOf(today));
        financialRec.setNextPayrollDate(Date.valueOf(nextPayrollDate));
        financialRec.setDaysUntilNextPayroll((int) daysUntilNextPayroll);
        financialRec.setExpenses(expenses);
        financialRec.setExtra(extra);

        return financialRecRepo.save(financialRec);
    }
    
    //     @PostMapping
    // public ResponseEntity<FinancialRec> addRecord(@RequestBody FinancialRec financialRec) {
    //     FinancialRec createdRecord = financialRecService.addRecord(financialRec);
    //     return ResponseEntity.ok(createdRecord);
    // }

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
