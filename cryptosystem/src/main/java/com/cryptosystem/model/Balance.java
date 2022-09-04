package com.cryptosystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Balance {
	@Id
	private String balanceId;
	@Column
	private double tokenValue;
	@Column
	private double tokenAmount;
	
	@OneToOne
	@JoinColumn(name="currency_id", referencedColumnName = "currencyId")
	private Cryptocurrency crypto;
	
	@ManyToOne
	@JoinColumn(name="walletId")
	private Wallet wallet;
	
	public Balance() {}
	
	public Balance(String balanceId, String currencyId, double tokenValue, double tokenAmount, Cryptocurrency crypto, Wallet wallet) {
		super();
		this.balanceId = balanceId;
		this.tokenValue = tokenValue;
		this.tokenAmount = tokenAmount;
		this.crypto = crypto;
		this.wallet = wallet;
	}

	public String getBalanceId() {
		return balanceId;
	}

	public void setId(String balanceId) {
		this.balanceId = balanceId;
	}
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

	public Cryptocurrency getCrypto() {
		return crypto;
	}

	public void setCrypto(Cryptocurrency crypto) {
		this.crypto = crypto;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
}
