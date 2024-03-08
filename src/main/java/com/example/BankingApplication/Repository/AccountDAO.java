package com.example.BankingApplication.Repository;

import com.example.BankingApplication.Entities.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountDAO extends JpaRepository<Accounts, Integer> {
    //
    public Accounts findByCustomerId(int customerId);

    public Accounts findByAccountNo(long accountNo);

    public List<Accounts> findAllByBalanceLessThan(double balanceAmt);

    public List<Accounts> findAllByBalanceGreaterThan(double balanceAmt);
}
