package com.cryptosystem.controller;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cryptosystem.model.Balance;
import com.cryptosystem.model.Cryptocurrency;
import com.cryptosystem.model.Transaction;
import com.cryptosystem.service.CryptoService;

@RestController
@RequestMapping("/api/v1/crypto")
public class CryptoController {
	private final CryptoService cryptoService;
	
	public CryptoController(final CryptoService cryptoService) {
		this.cryptoService = cryptoService;
	}

	@GetMapping("/transactions")
	public List<Transaction> transactions(@PathVariable String customerId) {
		return null;
	}

	@PostMapping("/trade")
	public Transaction trade(@RequestBody Transaction order) {
		return null;
	}

	@GetMapping("/balance/{customerId}")
	public List<Balance> userBalance(@PathVariable String customerId) {
		return null;
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
