package com.korobko;

import java.math.BigDecimal;
import java.util.List;

public interface QueryWalletService {
	public BigDecimal userAmount(Long userId);
	public List<Operation> history(Long userId);
}
