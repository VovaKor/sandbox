package com.korobko.repositories;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author Vova Korobko
 */
public interface Repository {
    Map<Long, BigDecimal> getAccounts();
}
