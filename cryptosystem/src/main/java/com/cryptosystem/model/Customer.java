package com.cryptosystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer {
	@Id
	private String customerId;
	@Column
	private String name;
	@Column
	private String email;
	
	@OneToOne
	@JoinColumn(name = "wallet_id", referencedColumnName = "walletId")
	private Wallet wallet;
	
	public Customer() {}
	
	public Customer(String customerId, String name, String email, Wallet wallet) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.wallet = wallet;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}
}