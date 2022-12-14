package com.masai.service;

import com.masai.exception.TransactionException;
import com.masai.model.Transaction;
import com.masai.model.Wallet;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    public Transaction addTransaction(Transaction transaction);

    public Transaction viewAllTransactions(Wallet wallet);

    public List<Transaction> viewTransactionBetweenDates(LocalDate dateFrom, LocalDate dateTo) throws TransactionException;

//    public List<Transaction> viewAllTransactionByType(String transactionType) throws TransactionException;
}
