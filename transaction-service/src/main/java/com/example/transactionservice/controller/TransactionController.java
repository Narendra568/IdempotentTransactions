package com.example.transactionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.transactionservice.model.TransactionRecord;
import com.example.transactionservice.service.TransactionProcessor;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionProcessor transactionProcessor;

    @Autowired
    public TransactionController(TransactionProcessor transactionProcessor) {
        this.transactionProcessor = transactionProcessor;
    }

    @PostMapping
    public ResponseEntity<TransactionRecord> createTransaction(@RequestBody TransactionRecord transactionRecord) {
        TransactionRecord createdTransaction = transactionProcessor.processTransaction(transactionRecord);
        return ResponseEntity.ok(createdTransaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionRecord> getTransaction(@PathVariable Long id) {
        TransactionRecord transactionRecord = transactionProcessor.getTransactionById(id);
        return transactionRecord != null ? ResponseEntity.ok(transactionRecord) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionRecord>> getAllTransactions() {
        List<TransactionRecord> transactions = transactionProcessor.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}