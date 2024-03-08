package com.example.BankingApplication.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TRANSACTIONS")
public class Transactions {

    @Id
    private String transactionId;
    private long fromAccountNumber;
    private long toAccountNumber;
    private double amount;

    public Transactions() {

    }

    public Transactions(String transactionId, long fromAccountNumber, long toAccountNumber, double amount) {
        this.transactionId = transactionId;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(long fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public long getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(long toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
