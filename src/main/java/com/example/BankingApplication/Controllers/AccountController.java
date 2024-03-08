package com.example.BankingApplication.Controllers;

import com.example.BankingApplication.Entities.Accounts;
import com.example.BankingApplication.Repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class AccountController {

    @Autowired
    AccountDAO accDao;

    // Register/Create Customer Record
    @PostMapping("/register")
    public String registerCustomer(@RequestBody Accounts acc) {
        if(accDao.existsById(acc.getCustomerId()))
            return "Customer Already Exists";
        accDao.save(acc);
        return acc.toString();
    }

    // Login Customer Account
    @PostMapping("/login")
    public Boolean loginCustomerAccount(@RequestBody Accounts acc) {
        if(!accDao.existsById(acc.getCustomerId())) {
            System.out.println("Customer Account Not Found");
            return false;
        }
        Accounts customerAcc = accDao.findByCustomerId(acc.getCustomerId());
        if(!Objects.equals(customerAcc.getPassword(), acc.getPassword())) {
            System.out.println("Invalid Credentials");
            return false;
        }
        return true;
    }

    // Change Password
    @PutMapping("/changepwd/{cid}/{oldPwd}/{newPwd}")
    public String changePassword(@PathVariable int cid, @PathVariable String oldPwd, @PathVariable String newPwd) {
        if(!accDao.existsById(cid))
            return "Customer Account Not Found";
        Accounts customerAcc = accDao.findByCustomerId(cid);
        if(!oldPwd.equals(customerAcc.getPassword()))
            return "Wrong Credentials";
        if(newPwd.equals(oldPwd))
            return "Please enter a password which is not used";
        customerAcc.setOldPassword(customerAcc.getPassword());
        customerAcc.setPassword(newPwd);
        accDao.save(customerAcc);
        return "Password Changed Successfully";
    }

    // Transfer
    @PostMapping("/transfer")
    public String transferAmount(@RequestParam("amount") double amount, @RequestParam("from") long fromAcc, @RequestParam("to") long toAcc, @RequestParam("ifsc") String toIfscCode) {
        Accounts fromCustomerAcc = accDao.findByAccountNo(fromAcc);
        Accounts toCustomerAcc = accDao.findByAccountNo(toAcc);
        if(fromCustomerAcc == null || toCustomerAcc == null)
            return "Enter correct account numbers";
        if(!Objects.equals(toIfscCode, toCustomerAcc.getIfscCode()))
            return "Wrong IFSC Code";
        if(fromCustomerAcc.getBalance() < amount)
            return "No enough Balance";
        fromCustomerAcc.setBalance(fromCustomerAcc.getBalance() - amount);
        toCustomerAcc.setBalance(toCustomerAcc.getBalance() + amount);
        accDao.save(fromCustomerAcc);
        accDao.save(toCustomerAcc);
        return "Transaction Successful";
    }

    // Display Balance Amount of an Account
    @GetMapping("/balance/{accountNo}")
    public double displayBalanceAmt(@PathVariable long accountNo) {
        Accounts customerAccount = accDao.findByAccountNo(accountNo);
        return customerAccount.getBalance();
    }

    // Accounts with balance below an amount
    @GetMapping("/accounts/below")
    public List<Accounts> getAccountsBelowAmt(@RequestParam("amount") double amt) {
        return accDao.findAllByBalanceLessThan(amt);
    }

    // Accounts with balance above an amount
    @GetMapping("/accounts/above")
    public List<Accounts> getAccountsAboveAmt(@RequestParam("amount") double amt) {
        return accDao.findAllByBalanceGreaterThan(amt);
    }


}
