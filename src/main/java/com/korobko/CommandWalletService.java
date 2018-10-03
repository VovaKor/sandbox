package com.korobko;

import java.math.BigDecimal;

public interface CommandWalletService {
    public boolean deposit(Long userId, BigDecimal amount);
    public boolean withdraw(Long userId, BigDecimal amount);
}
