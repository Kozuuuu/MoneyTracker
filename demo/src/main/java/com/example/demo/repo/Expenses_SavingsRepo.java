package com.example.demo.repo;

import java.util.Optional;

// import java.math.BigDecimal;
// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

import com.example.demo.model.Expenses_Savings;

// import jakarta.transaction.Transactional;

public interface Expenses_SavingsRepo extends JpaRepository <Expenses_Savings,  Long> {
    
    Optional<Expenses_Savings> findTopByOrderByIdDesc();
    
    // @Modifying
    // @Transactional
    // @Query(
    //     value = "UPDATE expenses_savings SET expenses = :expenses, expenses_description = :expenses_description WHERE no = 1", 
    //     nativeQuery = true
    // )
    // void updateExpensesDetails(
    //     @Param("expenses") BigDecimal expenses,
    //     @Param("expenses_description") String expenses_description
    // );
    
    // @Query(
    //     value = "SELECT expenses, expenses_description FROM expenses_savings WHERE no = 1",
    //     nativeQuery = true
    // )
    // List<Object[]> getExpensesDetails();


    // @Modifying
    // @Transactional
    // @Query(
    //     value = "UPDATE expenses_savings SET savings = :savings, savings_description = :savings_description WHERE no = 1",
    //     nativeQuery = true
    // )
    // void updateSavingsDetails(
    //     @Param("savings") BigDecimal savings,
    //     @Param("savings_descripton") String savings_description
    // );

    // @Query(
    //     value = "SELECT savings, savings_description FROM expenses_savings WHERE no = 1",
    //     nativeQuery = true
    // )
    // List<Object[]> getSavingsDetails();
}