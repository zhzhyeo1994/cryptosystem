package com.cryptosystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Balance {
	@Id
	private String id;
	private String walletId;
	private String currencyId;
	private double tokenValue;
	private int tokenAmount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

	public double getTokenValue() {
		return tokenValue;
	}

	public void setTokenValue(double tokenValue) {
		this.tokenValue = tokenValue;
	}

	public int getTokenAmount() {
		return tokenAmount;
	}

	public void setTokenAmount(int tokenAmount) {
		this.tokenAmount = tokenAmount;
	}
}
