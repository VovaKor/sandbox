package com.korobko;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vova Korobko
 */
public final class HistoryRepository {
    private static final HistoryRepository INSTANCE = new HistoryRepository();
    private Map<Long, Operation> operations = new ConcurrentHashMap<>();
    private HistoryRepository() {}
    public HistoryRepository getInstance() {
        return INSTANCE;
    }

    public Map<Long, Operation> getOperations() {
        return operations;
    }
}
