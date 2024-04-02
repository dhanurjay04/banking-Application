package com.bankingapp.service;

import com.bankingapp.entity.BankAccount;
import com.bankingapp.repository.BankAccountRepository;
import com.bankingapp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
@Autowired
    private EmailService emailService;

    @Autowired
    private TransactionRepository transactionRepository;



    @Autowired
    private BankAccountRepository bankAccountRepository;

    public BankAccount createBankAccount(BankAccount bankAccount) {



      return bankAccountRepository.save(bankAccount);


    }


    //find All Account
    public List<BankAccount> findAllAccountDetails() {
        return bankAccountRepository.findAll();
    }

    //delete a Account By AccountNumber
    public void deleteAccount(long accountNumber) {
        bankAccountRepository.deleteById(accountNumber);
    }


       //find a Account By AccountNumber
    public BankAccount findAccountByAccountNumber(long accountNumber) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber);
      return  bankAccount;
    }

    //Update Account
    public BankAccount updateAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    //deposit Rest Api



    public BankAccount deposit(Long accountNumber, Double amount) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            BankAccount updateAccount = bankAccountRepository.save(account);
            String emailSubject = "Deposit Notification";
            String emailText = "You have deposited $" + amount + " into your account.";
            emailService.sendEmail(updateAccount.getEmail(), emailSubject, emailText);


            return updateAccount;
        } else {
            return null;
        }
    }
    //withdraw Rest Api

    public BankAccount withdraw(Long accountNumber, double amount) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber);
        if (account != null && account.getBalance() >= amount) {
            double newBalance = account.getBalance() - amount;
            account.setBalance(newBalance);
            BankAccount upadateAccount = bankAccountRepository.save(account);

            return  upadateAccount;
        } else {
            return null;
        }
    }



}
