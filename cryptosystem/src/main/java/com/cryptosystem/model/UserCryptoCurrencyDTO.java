package com.cryptosystem.model;

public class UserCryptoCurrencyDTO {
	private double tokenValue;
	private double tokenAmount;
	private String symbol;
	
	public double getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(double tokenValue) {
		this.tokenValue = tokenValue;
	}
	public double getTokenAmount() {
		return tokenAmount;
	}
	public void setTokenAmount(double tokenAmount) {
		this.tokenAmount = tokenAmount;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
