package com.example.transactionservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.model.TransactionStatus;
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
        // TODO Auto-generated method stub
        System.out.println("Transaction Service Application is running...");
        Transaction transaction1 = new Transaction(123L, new java.math.BigDecimal("100.00"), TransactionStatus.PENDING);
        Transaction transaction2 = new Transaction(2L, new java.math.BigDecimal("200.00"), TransactionStatus.COMPLETED);
        Transaction transaction3 = new Transaction(3L, new java.math.BigDecimal("300.00"), TransactionStatus.FAILED);
        transactionRepository.save(transaction1);
        transactionRepository.save(transaction2);
        transactionRepository.save(transaction3);

    }
}