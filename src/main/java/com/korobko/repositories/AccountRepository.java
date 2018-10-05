package com.korobko.repositories;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vova Korobko
 */
public final class AccountRepository implements Repository {
    private static final Repository INSTANCE = new AccountRepository();
    private Map<Long, BigDecimal> accounts = new ConcurrentHashMap<>();
    private AccountRepository() {}
    public static Repository getInstance() {
        return INSTANCE;
    }

    public Map<Long, BigDecimal> getAccounts() {
        return accounts;
    }
}
