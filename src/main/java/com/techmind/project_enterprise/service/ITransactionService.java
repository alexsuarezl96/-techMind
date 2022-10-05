package com.techmind.project_enterprise.service;


import com.techmind.project_enterprise.model.Profile;
import com.techmind.project_enterprise.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<Transaction> getAllTransaction();
    Transaction saveTransaction (Transaction transaction);
    Transaction getTransactionById(Long id);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransactionById(Long id);
    boolean existSById(Long id);
    Optional<Transaction> getOne(Long id);
}
