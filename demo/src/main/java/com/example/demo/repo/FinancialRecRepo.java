package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.FinancialRec;

@Repository
public interface FinancialRecRepo extends JpaRepository<FinancialRec, Long> {
}
