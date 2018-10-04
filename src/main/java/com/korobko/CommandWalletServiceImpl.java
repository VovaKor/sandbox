package com.korobko;

import java.math.BigDecimal;

/**
 * @author Vova Korobko
 */
public class CommandWalletServiceImpl implements CommandWalletService {
    @Override
    public boolean deposit(Long userId, BigDecimal amount) {
        return false;
    }

    @Override
    public boolean withdraw(Long userId, BigDecimal amount) {
        return false;
    }
}
