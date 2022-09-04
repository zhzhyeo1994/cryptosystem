package com.cryptosystem.controller;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptosystem.model.Cryptocurrency;
import com.cryptosystem.model.OrderDTO;
import com.cryptosystem.model.Transaction;
import com.cryptosystem.model.UserCryptoCurrencyDTO;
import com.cryptosystem.model.UserCryptoPortfolioDTO;
import com.cryptosystem.service.CryptoService;
import com.cryptosystem.service.CustomerService;
import com.cryptosystem.service.TransactionService;

@RestController
@RequestMapping("/api/v1/crypto")
public class CryptoController {
	private final CryptoService cryptoService;
	private final CustomerService customerService;
	private final TransactionService transactionService;
	
	public CryptoController(final CryptoService cryptoService, final CustomerService customerService, final TransactionService transactionService) {
		this.cryptoService = cryptoService;
		this.customerService = customerService;
		this.transactionService = transactionService;
	}

	@GetMapping("/transactions/{customerId}")
	public List<Transaction> transactions(@PathVariable String customerId) {
		return this.transactionService.transactions(customerId);
	}

	@PostMapping("/trade")
	public void trade(@RequestBody OrderDTO order) {
		this.transactionService.trade(order);
	}

	@GetMapping("/portfolio/{customerId}")
	public UserCryptoPortfolioDTO userPortfolio(@PathVariable String customerId) {
		return this.customerService.getAllCryptoByCustomerId(customerId);
	}
	
	@GetMapping("/token/{customerId}")
	public UserCryptoCurrencyDTO userCrypto(@PathVariable String customerId, @RequestParam(required=true) String currencyId) {
		return this.customerService.getCryptoByCustomerIdAndCurrencyId(customerId, currencyId);
	}
	
	@GetMapping("/currencies")
	public List<Cryptocurrency> getBestLatestPrices() {
		return this.cryptoService.getPrices();
	}
	
	@GetMapping("/currency/{currencyId}")
	public Cryptocurrency getBestLatestPriceByCurrencyId(@PathVariable String currencyId) {
		return this.cryptoService.getPriceByCurrencyId(currencyId);
	}
	
	@Scheduled(fixedRate=10000)
	public void updatePrice() {
		this.cryptoService.retrieveAndUpdateLatestPrice();
	}

}
