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

import com.example.demo.model.Expenses_Savings;
import com.example.demo.model.FinancialRec;
import com.example.demo.repo.Expenses_SavingsRepo;
import com.example.demo.repo.FinancialRecRepo;

@Service
public class FinancialRecService {

    @Autowired
    private FinancialRecRepo financialRecRepo;

    @Autowired
    private Expenses_SavingsRepo expenses_SavingsRepo;

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
            
            record.setCash(updatedRecord.getCash() != null ? updatedRecord.getCash() : record.getCash());
            record.setDate(updatedRecord.getDate() != null ? updatedRecord.getDate() : record.getDate());
            record.setSavings(updatedRecord.getSavings() != null ? updatedRecord.getSavings() : record.getSavings());
            record.setExtra(updatedRecord.getExtra() != null ? updatedRecord.getExtra() : record.getExtra());
            record.setPeriod(updatedRecord.getPeriod() != null ? updatedRecord.getPeriod() : record.getPeriod());
            
            return financialRecRepo.save(record);
        }
        return null;
    }

    public FinancialRec addRecord(FinancialRec financialRec) {
        LocalDate today = LocalDate.now();
        LocalDate nextPayrollDate = calculateNextPayrollDate(today);
        long daysUntilNextPayroll = ChronoUnit.DAYS.between(today, nextPayrollDate);

        Expenses_Savings defaultValues = expenses_SavingsRepo.findTopByOrderByIdDesc()
            .orElseThrow();

        BigDecimal expenses = financialRec.getExpenses() != null ?
            financialRec.getExpenses() :
            BigDecimal.valueOf(daysUntilNextPayroll * 150).add(BigDecimal.valueOf(500));
        
        BigDecimal savings = financialRec.getSavings() != null ?
            financialRec.getSavings() :
            defaultValues.getSavings();

        BigDecimal extra = financialRec.getCash().subtract(expenses).subtract(savings);

        financialRec.setDate(Date.valueOf(today));
        financialRec.setNextPayrollDate(Date.valueOf(nextPayrollDate));
        financialRec.setDaysUntilNextPayroll((int) daysUntilNextPayroll);
        financialRec.setExpenses(expenses);
        financialRec.setSavings(savings);
        financialRec.setExtra(extra);

        return financialRecRepo.save(financialRec);
    }

    private LocalDate calculateNextPayrollDate(LocalDate today) {
        LocalDate nextPayrollDate;
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        LocalDate fifteenthOfMonth = today.withDayOfMonth(15);
    
        if (today.getDayOfMonth() <= 15) {
            if (hasReceivedPayrollFor15th(today)) {
                nextPayrollDate = lastDayOfMonth;
            } else {
                nextPayrollDate = fifteenthOfMonth;
            }
        } else {
            if (hasReceivedPayrollForTheLastDay(today)) {
                nextPayrollDate = fifteenthOfMonth;
            } else {
            nextPayrollDate = lastDayOfMonth;
            }
        }
    
        if (nextPayrollDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            nextPayrollDate = nextPayrollDate.minusDays(1);
        } else if (nextPayrollDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            nextPayrollDate = nextPayrollDate.minusDays(2);
        }
    
        return nextPayrollDate;
    }
    
    private boolean hasReceivedPayrollFor15th(LocalDate today) {
        return today.isBefore(today.withDayOfMonth(15)) || today.isEqual(today.withDayOfMonth(15));
    }
    private boolean hasReceivedPayrollForTheLastDay(LocalDate today) {
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        return today.isBefore(lastDayOfMonth) && today.isAfter(today.withDayOfMonth(15)) || today.isEqual(lastDayOfMonth);
    }
}