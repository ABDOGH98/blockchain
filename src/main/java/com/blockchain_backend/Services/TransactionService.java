package com.blockchain_backend.Services;


import com.blockchain_backend.Entities.Transaction;
import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
    public void signTransaction(Transaction transaction);
}

