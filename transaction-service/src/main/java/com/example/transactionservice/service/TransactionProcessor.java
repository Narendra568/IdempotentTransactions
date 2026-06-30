package com.example.transactionservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.transactionservice.model.TransactionRecord;
import com.example.transactionservice.repository.TransactionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionProcessor {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionProcessor(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public TransactionRecord processTransaction(TransactionRecord transactionRecord) {
        // Check if the transaction has already been processed
        Optional<TransactionRecord> existingTransaction = transactionRepository.findById(transactionRecord.getTransactionId());
        if (existingTransaction.isPresent()) {
            return existingTransaction.get(); // Return the existing transaction to ensure idempotency
        }

        // Process the transaction (e.g., save it to the database)
        return transactionRepository.save(transactionRecord);
    }

    public TransactionRecord getTransactionById(Long id) {
        return Optional.of(transactionRepository.findById(id)).isPresent()
                ? transactionRepository.findById(id).get()
                : null;
        //throw new UnsupportedOperationException("Unimplemented method 'getTransactionById'");
    }

    public List<TransactionRecord> getAllTransactions() {
          return transactionRepository.findAll();
       // throw new UnsupportedOperationException("Unimplemented method 'getAllTransactions'");
    }
}