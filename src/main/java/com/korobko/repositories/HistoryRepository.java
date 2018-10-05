package com.korobko.repositories;

import com.korobko.models.Operation;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Vova Korobko
 */
public final class HistoryRepository {
    private static final HistoryRepository INSTANCE = new HistoryRepository();
    private Map<Long, List<Operation>> operations = new ConcurrentHashMap<>();
    private HistoryRepository() {}
    public static HistoryRepository getInstance() {
        return INSTANCE;
    }

    public Map<Long, List<Operation>> getOperations() {
        return operations;
    }
}
