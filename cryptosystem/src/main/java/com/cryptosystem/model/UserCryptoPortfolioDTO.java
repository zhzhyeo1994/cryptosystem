package com.cryptosystem.model;

import java.util.List;

public class UserCryptoPortfolioDTO {
	private List<UserCryptoCurrencyDTO> cryptoList;
	private double totalValue;
	
	public List<UserCryptoCurrencyDTO> getCryptoList() {
		return cryptoList;
	}
	public void setCryptoList(List<UserCryptoCurrencyDTO> cryptoList) {
		this.cryptoList = cryptoList;
	}
	public double getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}
}
