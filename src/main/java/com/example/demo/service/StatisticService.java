package com.example.demo.service;

import com.example.demo.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final TransactionRepository transactionRepo;
    public Object getProductTransactionCounts() {
        return transactionRepo.findProductTransactionCounts();
    }
}
