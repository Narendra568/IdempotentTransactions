package com.example.transactionservice.dto;

import com.example.transactionservice.model.TransactionStatus;

public class TransactionResponse {
    private Long transactionId;
    private String status;
    private String message;

    public TransactionResponse(Long transactionId, String status, String message) {
        this.transactionId = transactionId;
        this.status = status;
        this.message = message;
    }

    public TransactionResponse(Long id, TransactionStatus status2) {
        }

    public TransactionResponse(long l, String string) {
        //TODO Auto-generated constructor stub
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}