package com.bankingapp.controller;

import com.bankingapp.entity.BankAccount;
import com.bankingapp.entity.Transaction;
import com.bankingapp.entity.TransactionType;
import com.bankingapp.repository.BankAccountRepository;
import com.bankingapp.service.BankAccountService;
import com.bankingapp.service.EmailService;
import com.bankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class BankAccountController {



    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountService bankAccountService;

    @PostMapping("/createAccount")
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        BankAccount createdbankAccount = bankAccountService.createBankAccount(bankAccount);
return  new ResponseEntity<>(createdbankAccount,HttpStatus.CREATED);
    }

    @GetMapping("/AllAccountDetails")
public ResponseEntity<List<BankAccount>> findAllAccountDetails(){

        List<BankAccount> allAccountDetails = bankAccountService.findAllAccountDetails();
        return  new ResponseEntity<>(allAccountDetails,HttpStatus.OK);
}

@GetMapping("/accounts/{accountNumber}")
    public ResponseEntity<BankAccount> findAccountByAccountNumber(@PathVariable Long accountNumber) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
@DeleteMapping("/accounts/{accountNumber}")
public ResponseEntity<String> deleteAccount(@PathVariable long accountNumber){

    bankAccountRepository.deleteByAccountNumber(accountNumber);

    return  new ResponseEntity<>("Account is deleted permanently",HttpStatus.OK);
}




    @PutMapping("/accounts/{accountNumber}")
    public ResponseEntity<BankAccount> updateAccount(@PathVariable Long accountNumber, @RequestBody BankAccount bankAccount) {
        BankAccount existingAccount = bankAccountService.findAccountByAccountNumber(accountNumber);
        if (existingAccount != null) {

            existingAccount.setAccountNumber(bankAccount.getAccountNumber());
            existingAccount.setEmail(bankAccount.getEmail());
            existingAccount.setMobileNumber(bankAccount.getMobileNumber());
            BankAccount updateAccount = bankAccountService.updateAccount(bankAccount);

            return new ResponseEntity<>(updateAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
   }




    @PostMapping("/accounts/{accountNumber}/deposit")
    public ResponseEntity<BankAccount> deposit(@PathVariable Long accountNumber, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        BankAccount updatedAccount = bankAccountService.deposit(accountNumber, amount);
        if (updatedAccount != null) {
            // Create deposit transaction
            Transaction transaction = transactionService.createTransaction(updatedAccount, amount, TransactionType.DEPOSIT);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/accounts/{accountNumber}/withdraw")
    public ResponseEntity<BankAccount> withdraw(@PathVariable Long accountNumber, @RequestBody Map<String, Double> request) {
        double amount = request.get("amount");
        BankAccount updatedAccount = bankAccountService.withdraw(accountNumber, amount);
        if (updatedAccount != null) {
            // Create withdrawal transaction
            Transaction transaction = transactionService.createTransaction(updatedAccount, amount, TransactionType.WITHDRAWAL);
            return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
