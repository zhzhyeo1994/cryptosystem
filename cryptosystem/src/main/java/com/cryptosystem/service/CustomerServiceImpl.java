package com.cryptosystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cryptosystem.model.Balance;
import com.cryptosystem.model.Customer;
import com.cryptosystem.model.Transaction;
import com.cryptosystem.model.UserCryptoCurrencyDTO;
import com.cryptosystem.model.UserCryptoPortfolioDTO;
import com.cryptosystem.repository.CustomerRepository;

@Component
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;

	public CustomerServiceImpl(final CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Override
	public Customer getCustomerInfo(String customerId) {
		return this.customerRepository.findByCustomerId(customerId);
	}

	@Override
	public UserCryptoCurrencyDTO getCryptoByCustomerIdAndCurrencyId(String customerId, String currencyId) {
		UserCryptoPortfolioDTO portfolio = this.getAllCryptoByCustomerId(customerId);
		return portfolio.getCryptoList().stream().filter(crypto -> crypto.getSymbol().equalsIgnoreCase(currencyId))
				.findAny().orElse(null);
	}

	@Override
	public UserCryptoPortfolioDTO getAllCryptoByCustomerId(String customerId) {
		Customer customer = this.customerRepository.findByCustomerId(customerId);
		UserCryptoPortfolioDTO portfolio = new UserCryptoPortfolioDTO();
		double totalValue = 0;
		List<UserCryptoCurrencyDTO> cryptoList = new ArrayList<>();
		for (Balance bal : customer.getWallet().getWalletBalance()) {
			totalValue += bal.getTokenValue();
			UserCryptoCurrencyDTO crypto = new UserCryptoCurrencyDTO();
			crypto.setSymbol(bal.getCrypto().getCurrencyId());
			crypto.setTokenAmount(bal.getTokenAmount());
			crypto.setTokenValue(bal.getTokenValue());
			cryptoList.add(crypto);
		}
		portfolio.setCryptoList(cryptoList);
		portfolio.setTotalValue(totalValue);
		return portfolio;
	}

}
