package com.bankingapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BankAccountDto {

    private Long id;
    private Long accountNumber;
    private String accountHolderName;
    private LocalDate localDate;
    private String email;
    private String adharCard;
    private String mobileNumber;
    private double balance;

}
