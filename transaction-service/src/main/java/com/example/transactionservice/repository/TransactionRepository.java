package com.example.transactionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.transactionservice.model.TransactionRecord;

public interface TransactionRepository extends JpaRepository<TransactionRecord, Long> {
}