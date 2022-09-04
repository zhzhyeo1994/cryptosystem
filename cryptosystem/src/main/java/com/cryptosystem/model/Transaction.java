package com.cryptosystem.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Transaction {
	@Id
	private String transactionId;
	@Column
	private String walletId;
	@Column
	private String txnId;
	@Column
	private String status;
	@Column
	private double totalCost;
	@Column
	private double tokenAmount;
	@Column
	private String type;
	@Column
	private String paymentBy;
	@Column
	private Date tradeDate;

	@OneToOne
	@JoinColumn(name = "currency_id", referencedColumnName = "currencyId")
	private Cryptocurrency crypto;
	
	public Transaction() {}

	public Transaction(String transactionId, String txnId, String status, double totalCost, double tokenAmount,
			String type, String paymentBy, Cryptocurrency crypto) {
		super();
		this.transactionId = transactionId;
		this.txnId = txnId;
		this.status = status;
		this.totalCost = totalCost;
		this.tokenAmount = tokenAmount;
		this.type = type;
		this.paymentBy = paymentBy;
		this.crypto = crypto;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}


	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTokenAmount() {
		return tokenAmount;
	}

	public void setTokenAmount(double tokenAmount) {
		this.tokenAmount = tokenAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPaymentBy() {
		return paymentBy;
	}

	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
	}

	public Cryptocurrency getCrypto() {
		return crypto;
	}

	public void setCrypto(Cryptocurrency crypto) {
		this.crypto = crypto;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

}
