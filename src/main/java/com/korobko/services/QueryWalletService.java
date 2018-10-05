package com.korobko.services;

import com.korobko.models.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface QueryWalletService {
	BigDecimal userAmount(Long userId);
	List<Operation> history(Long userId);
}
