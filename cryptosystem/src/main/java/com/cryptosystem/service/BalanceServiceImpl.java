package com.cryptosystem.service;

import org.springframework.stereotype.Component;

import com.cryptosystem.model.Balance;
import com.cryptosystem.repository.BalanceRepository;

@Component
public class BalanceServiceImpl implements BalanceService {
	private final BalanceRepository balanceRepository;
	
	public BalanceServiceImpl(final BalanceRepository balanceRepository) {
		this.balanceRepository = balanceRepository;
	}

	@Override
	public Balance updateBalance(String balanceId, Balance toUpdate) {
		Balance updateObj = this.balanceRepository.findByBalanceId(balanceId);
		updateObj.setTokenAmount(toUpdate.getTokenAmount());
		updateObj.setTokenValue(toUpdate.getTokenValue());
		return this.balanceRepository.save(updateObj);
	}

	@Override
	public Balance createNewBalance(Balance newBalance) {
		return this.balanceRepository.save(newBalance);
	}
	
}
