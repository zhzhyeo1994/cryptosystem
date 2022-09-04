package com.cryptosystem.service;

import com.cryptosystem.model.Balance;

public interface BalanceService {
	Balance updateBalance(String balanceId, Balance toUpdate);
	
	Balance createNewBalance(Balance newBalance);
}
