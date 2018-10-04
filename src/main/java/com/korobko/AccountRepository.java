package com.korobko;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vova Korobko
 */
public final class AccountRepository {
    private static final AccountRepository INSTANCE = new AccountRepository();
    private Map<Long, AtomicBigDecimal> accounts = new ConcurrentHashMap<>();
    private AccountRepository() {}
    public AccountRepository getInstance() {
        return INSTANCE;
    }

    public Map<Long, AtomicBigDecimal> getAccounts() {
        return accounts;
    }
}
