package com.bankingapp.controller;

import com.bankingapp.entity.Transaction;
import com.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions/{accountNumber}")
    public ResponseEntity<?> getTransactionsByAccountNumber(@PathVariable Long accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        if (!transactions.isEmpty()) {
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No transactions found for the account number.", HttpStatus.OK);
        }
    }


}
