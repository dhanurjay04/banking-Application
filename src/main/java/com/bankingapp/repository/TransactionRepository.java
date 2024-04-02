package com.bankingapp.repository;

import com.bankingapp.entity.BankAccount;
import com.bankingapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findByAccount(BankAccount account);


}
