package com.cryptosystem.exception;

public class PriceChangedException extends RuntimeException {
	public PriceChangedException(String currencyId, double currentPrice) {
        super("Changes in price, order price is too low to complete order for: " + currencyId + " with the current price of " + currentPrice);
    }
}
