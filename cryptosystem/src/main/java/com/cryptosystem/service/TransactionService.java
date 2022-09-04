package com.cryptosystem.service;

import java.util.List;

import com.cryptosystem.model.OrderDTO;
import com.cryptosystem.model.Transaction;

public interface TransactionService {
	List<Transaction> transactions(String customerId);
	
	void trade(OrderDTO order);
}
