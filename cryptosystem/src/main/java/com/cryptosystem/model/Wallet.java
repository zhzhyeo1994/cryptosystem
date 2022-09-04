package com.cryptosystem.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Wallet {
	@Id
	private String walletId;
	@Column
	private String addrress;
	@Column
	private String transactionId;

	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Balance> walletBalance = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="walletId")
	private List<Transaction> transactions = new ArrayList<>();
	
	public Wallet() {}
	
	public Wallet(String walletId, String addrress, String transactionId) {
		super();
		this.walletId = walletId;
		this.addrress = addrress;
		this.transactionId = transactionId;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public String getAddrress() {
		return addrress;
	}

	public void setAddrress(String addrress) {
		this.addrress = addrress;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public List<Balance> getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(List<Balance> walletBalance) {
		this.walletBalance = walletBalance;
	}

}
