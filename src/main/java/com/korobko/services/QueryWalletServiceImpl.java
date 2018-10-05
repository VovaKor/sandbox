package com.korobko.services;

import com.korobko.models.Operation;
import com.korobko.repositories.AccountRepository;
import com.korobko.repositories.HistoryRepository;
import com.korobko.services.QueryWalletService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Vova Korobko
 */
public class QueryWalletServiceImpl implements QueryWalletService {
    @Override
    public BigDecimal userAmount(Long userId) {
        return AccountRepository.getInstance().getAccounts().get(userId);
    }

    @Override
    public List<Operation> history(Long userId) {
        return HistoryRepository.getInstance().getOperations().get(userId);
    }
}
