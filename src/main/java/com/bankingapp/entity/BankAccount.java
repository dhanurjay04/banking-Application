package com.bankingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long accountNumber;
    private String accountHolderName;
    private LocalDate localDate;
    private String email;
    private String adharCard;
    private String mobileNumber;
    private double balance;

    public long getId() {
        return id;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getEmail() {
        return email;
    }

    public String getAdharCard() {
        return adharCard;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdharCard(String adharCard) {
        this.adharCard = adharCard;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
