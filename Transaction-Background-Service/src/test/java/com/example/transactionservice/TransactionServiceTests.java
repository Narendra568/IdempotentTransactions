package com.example.transactionservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.transactionservice.dto.TransactionRequest;
import com.example.transactionservice.dto.TransactionResponse;
import com.example.transactionservice.exception.DuplicateTransactionException;
import com.example.transactionservice.exception.IdempotencyViolationException;
import com.example.transactionservice.exception.TransactionNotFoundException;
import com.example.transactionservice.model.Transaction;
import com.example.transactionservice.model.TransactionStatus;
import com.example.transactionservice.repository.TransactionRepository;
import com.example.transactionservice.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

class TransactionServiceTests {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;
    private TransactionRequest transactionRequest;
    private Transaction transaction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionRequest = new TransactionRequest();
        transactionRequest.setTransactionId(123L);
        transactionRequest.setAmount(new java.math.BigDecimal("100.0"));
        transactionRequest.setCurrency("USD");
        transactionRequest.setDescription("Test transaction");

        transaction = new Transaction();
        transaction.setTransactionId(123L);
        transaction.setStatus(TransactionStatus.PENDING);
    }

    @Test
    void testSubmitTransaction_Success() {
        when(transactionRepository.findById(transaction.getTransactionId())).thenReturn(Optional.empty());
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionResponse result = transactionService.submitTransaction(transactionRequest);

        assertEquals(TransactionStatus.PENDING, result.getStatus());
        verify(transactionRepository).save(transaction);
    }

    @Test
    void testSubmitTransaction_Duplicate() {
        when(transactionRepository.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));

        assertThrows(DuplicateTransactionException.class, () -> transactionService.submitTransaction(transactionRequest));
        verify(transactionRepository, never()).save(any());
    }

    @Test
    void testGetTransactionStatus_Success() {
        when(transactionRepository.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));

        TransactionResponse result = transactionService.getTransactionStatus(transaction.getTransactionId());

        assertEquals(transaction.getTransactionId(), result.getTransactionId());
        assertEquals(TransactionStatus.PENDING, result.getStatus());
    }

    @Test
    void testGetTransactionStatus_NotFound() {
        when(transactionRepository.findById(transaction.getTransactionId())).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> transactionService.getTransactionStatus(transaction.getTransactionId()));
    }

    @Test
    void testSubmitTransaction_IdempotencyViolation() {
        transaction.setStatus(TransactionStatus.COMPLETED);
        when(transactionRepository.findById(transaction.getTransactionId())).thenReturn(Optional.of(transaction));

        assertThrows(IdempotencyViolationException.class, () -> transactionService.submitTransaction(transactionRequest));
    }
}