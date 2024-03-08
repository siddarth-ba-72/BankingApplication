package com.example.BankingApplication.Repository;

import com.example.BankingApplication.Entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsDAO extends JpaRepository<Transactions, Integer> {
    //
}
