package com.cryptosystem.exception;

public class InsufficientValueException extends RuntimeException {
	private static final long serialVersionUID = -3080169681543710499L;
	public InsufficientValueException(String currencyId) {
        super("Insufficient value to trade cryptocurrency: " + currencyId);
    }
}