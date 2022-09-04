package com.cryptosystem.service;

import com.cryptosystem.model.Customer;
import com.cryptosystem.model.UserCryptoCurrencyDTO;
import com.cryptosystem.model.UserCryptoPortfolioDTO;

public interface CustomerService {
	UserCryptoCurrencyDTO getCryptoByCustomerIdAndCurrencyId(String customerId, String currencyId);

	UserCryptoPortfolioDTO getAllCryptoByCustomerId(String customerId);
	
	Customer getCustomerInfo(String customerId);
}
