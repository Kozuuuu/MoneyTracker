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
            record.setMonthlyPayments(updatedRecord.getMonthlyPayments() != null ? updatedRecord.getMonthlyPayments() : record.getMonthlyPayments());

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

        BigDecimal savings = financialRec.getSavings() != null ?
            financialRec.getSavings() :
            defaultValues.getSavings();

        BigDecimal monthlyPayments = financialRec.getMonthlyPayments() != null ?
            financialRec.getMonthlyPayments() :
            defaultValues.getMonthlyPayments();

        BigDecimal expenses = financialRec.getExpenses() != null ?
            financialRec.getExpenses().add(monthlyPayments) :
            BigDecimal.valueOf(daysUntilNextPayroll * 150).add(BigDecimal.valueOf(500)).add(monthlyPayments);
        
        BigDecimal extra = financialRec.getCash().subtract(expenses).subtract(savings);

        financialRec.setDate(Date.valueOf(today));
        financialRec.setNextPayrollDate(Date.valueOf(nextPayrollDate));
        financialRec.setDaysUntilNextPayroll((int) daysUntilNextPayroll);
        financialRec.setExpenses(expenses);
        financialRec.setMonthlyPayments(monthlyPayments);
        financialRec.setSavings(savings);
        financialRec.setExtra(extra);

        return financialRecRepo.save(financialRec);
    }

    private LocalDate calculateNextPayrollDate(LocalDate today) {
        LocalDate nextPayrollDate;
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        LocalDate fifteenthOfMonth = today.withDayOfMonth(15);

        if (today.getDayOfMonth() <= 20 && today.getDayOfMonth() >= 10) {
            if (hasReceivedPayrollFor15th(today)) {
                nextPayrollDate = lastDayOfMonth;
            } else {
                nextPayrollDate = fifteenthOfMonth;
            }
        } else if (today.getDayOfMonth() >= 21 && today.getDayOfMonth() <= 31 ) {
            if (hasReceivedPayrollForTheLastDay(today)) {
                nextPayrollDate = today.plusMonths(1).withDayOfMonth(15);
            } else {
                nextPayrollDate = lastDayOfMonth;
            }
        } else if (today.getDayOfMonth() >= 1 && today.getDayOfMonth() <= 9){
            nextPayrollDate = today.withDayOfMonth(15);
        } else {
            return today;
        }

        // Adjust for weekends
        if (nextPayrollDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
            nextPayrollDate = nextPayrollDate.minusDays(1);
        } else if (nextPayrollDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            nextPayrollDate = nextPayrollDate.minusDays(2);
        }
        return nextPayrollDate;
    }

    private boolean hasReceivedPayrollFor15th(LocalDate today) {
        LocalDate twentiethOfMonth = today.withDayOfMonth(20);
        LocalDate tenthOfMonth = today.withDayOfMonth(10);

        return today.isBefore(twentiethOfMonth) && today.isAfter(tenthOfMonth) || today.isEqual(twentiethOfMonth) && today.isAfter(tenthOfMonth);
    }

    private boolean hasReceivedPayrollForTheLastDay(LocalDate today) {
        LocalDate lastDayOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        LocalDate twentiethOfMonth = today.withDayOfMonth(20);

        return today.isBefore(lastDayOfMonth) && today.isAfter(twentiethOfMonth) || today.isEqual(lastDayOfMonth) && today.isAfter(twentiethOfMonth);
    }
}