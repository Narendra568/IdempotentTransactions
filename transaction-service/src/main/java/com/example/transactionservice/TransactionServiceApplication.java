package com.example.transactionservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.transactionservice.model.TransactionRecord;
import com.example.transactionservice.repository.TransactionRepository;

@SpringBootApplication
public class TransactionServiceApplication implements CommandLineRunner {

    final TransactionRepository transactionRepository;

    TransactionServiceApplication(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransactionServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Initialization logic can be added here
        System.out.println("Transaction Service initialized.");
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setAmount(100.0);
        transactionRecord.setStatus("PENDING");
        transactionRecord.setCreatedAt(System.currentTimeMillis());
        transactionRecord.setUpdatedAt(System.currentTimeMillis());
        transactionRecord.setTransactionId(1L); // Set a sample ID for demonstration
        transactionRecord.setSequenceNumber(1L);
        transactionRecord.setRequestId(1L);
        transactionRepository.save(transactionRecord);
        System.out.println("Sample Transaction Record: " + transactionRecord);
    }
}