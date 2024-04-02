package com.bankingapp.service;

import com.bankingapp.entity.BankAccount;
import com.bankingapp.entity.Transaction;
import com.bankingapp.entity.TransactionType;
import com.bankingapp.repository.BankAccountRepository;
import com.bankingapp.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {


    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    public List<Transaction> getTransactionsByAccountNumber(Long accountNumber) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return transactionRepository.findByAccount(account);
        } else {
            return Collections.emptyList();
        }
    }

    public Transaction createTransaction(BankAccount account, Double amount, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTransactionType(type);
        transaction.setTransactionDateTime(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

}
