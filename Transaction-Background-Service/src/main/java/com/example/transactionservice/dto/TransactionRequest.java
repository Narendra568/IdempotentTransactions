package com.example.transactionservice.dto;

import java.math.BigDecimal;

import com.example.transactionservice.model.TransactionStatus;

public class TransactionRequest {
    private Long transactionId;
    private BigDecimal amount;
    private String currency;
    private String description;
    private TransactionStatus status;

    public TransactionRequest() {
    }

    public TransactionRequest(Long transactionId, BigDecimal amount, String currency, String description) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}