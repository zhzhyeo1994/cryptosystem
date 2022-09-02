package com.cryptosystem.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cryptosystem.model.Balance;
import com.cryptosystem.model.Transaction;

@RestController
@RequestMapping("/api/v1/crypto")
public class CryptoController {

	public CryptoController() {
		// TODO Auto-generated constructor stub
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

}
