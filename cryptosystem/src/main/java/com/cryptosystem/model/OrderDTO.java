package com.cryptosystem.model;

public class OrderDTO {
	private String currencyId;
	private String orderType;
	private double tokenAmount;
	private double orderPrice;
	private String customerId;
	
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String tradeType) {
		this.orderType = tradeType;
	}
	public double getTokenAmount() {
		return tokenAmount;
	}
	public void setTokenAmount(double tokenAmount) {
		this.tokenAmount = tokenAmount;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
