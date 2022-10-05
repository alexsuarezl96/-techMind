package com.techmind.project_enterprise.service.Impl;

import com.techmind.project_enterprise.model.Transaction;
import com.techmind.project_enterprise.repository.ITransactionRepository;
import com.techmind.project_enterprise.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements ITransactionService {
    @Autowired
    private ITransactionRepository repotransaction;
    @Override
    public List<Transaction> getAllTransaction() {

        return repotransaction.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {

        return repotransaction.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {

        return repotransaction.findById(id).get();
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {

        return repotransaction.save(transaction);
    }

    @Override
    public void deleteTransactionById(Long id) {

        repotransaction.deleteById(id);
    }

    @Override
    public boolean existSById(Long id) {

        return repotransaction.existsById(id);
    }

    @Override
    public Optional<Transaction> getOne(Long id) {

        return repotransaction.findById(id);
    }
}
