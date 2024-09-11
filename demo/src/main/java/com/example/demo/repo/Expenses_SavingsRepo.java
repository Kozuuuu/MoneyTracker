package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Expenses_Savings;

public interface Expenses_SavingsRepo extends JpaRepository <Expenses_Savings,  Long> {

}
