package com.cryptosystem.service;

import java.util.List;

import com.cryptosystem.model.Cryptocurrency;

public interface CryptoService {
	void retrieveAndUpdateLatestPrice();

	Cryptocurrency updatePrice(String symbol, double bestBidPrice, double bestAskPrice);
	
	List<Cryptocurrency> getPrices();
	
	Cryptocurrency getPriceByCurrencyId(String currencyId);
}
