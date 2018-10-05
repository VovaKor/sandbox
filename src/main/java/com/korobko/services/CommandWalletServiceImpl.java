package com.korobko.services;

import com.korobko.repositories.AccountRepository;
import com.korobko.services.CommandWalletService;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Vova Korobko
 */
public class CommandWalletServiceImpl implements CommandWalletService {
    @Override
    public boolean deposit(Long userId, BigDecimal amount) {
        if (isAmountValid(amount)) {
            AccountRepository
                    .getInstance()
                    .getAccounts()
                    .computeIfPresent(userId, (k, v) -> v.add(amount));
            return true;
        }
        return false;
    }

    @Override
    public boolean withdraw(Long userId, BigDecimal amount) {
        if (isAmountValid(amount)) {
            AtomicBoolean result = new AtomicBoolean();
            AccountRepository
                    .getInstance()
                    .getAccounts()
                    .computeIfPresent(userId, (k, v) -> {
                        if (v.compareTo(amount) >= 0) {
                            result.compareAndSet(false, true);
                            return v.subtract(amount);
                        }
                        return v;
                    });
            return result.get();
        }
        return false;
    }

    private boolean isAmountValid(BigDecimal amount) {
       return Objects.nonNull(amount) && nonNegative(amount);
    }

    private boolean nonNegative(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }
}
