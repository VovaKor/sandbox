package com.korobko.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation {
    private Long userId;
	private OperationType operation;
	private BigDecimal amount;
	private LocalDateTime timestamp;
	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * @return the timestamp
	 */
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	/**
	 * @return the operation
	 */
	public OperationType getOperation() {
		return operation;
	}
	/**
	 * @param operation the operation to set
	 */
	public void setOperation(OperationType operation) {
		this.operation = operation;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

}