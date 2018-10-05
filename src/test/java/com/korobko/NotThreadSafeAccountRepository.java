package com.korobko;

import com.korobko.repositories.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Vova Korobko
 */
public final class NotThreadSafeAccountRepository implements Repository {
    private static final Repository INSTANCE = new NotThreadSafeAccountRepository();
    private Map<Long, BigDecimal> accounts = new HashMap<>();
    private NotThreadSafeAccountRepository() {}
    public static Repository getInstance() {
        return INSTANCE;
    }

    public Map<Long, BigDecimal> getAccounts() {
        return accounts;
    }
}
