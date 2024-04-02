package com.bankingapp.repository;

import com.bankingapp.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {
    void deleteByAccountNumber(Long accountNumber);
    BankAccount findByAccountNumber(Long accountNumber);


}
