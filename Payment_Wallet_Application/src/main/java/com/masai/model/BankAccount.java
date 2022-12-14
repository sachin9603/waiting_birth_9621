package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class BankAccount {

    private Integer accountNo;
    private String ifscCode;
    private String bankName;
    private double balance;
    private Wallet wallet;

}
