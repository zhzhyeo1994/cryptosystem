package com.cryptosystem.service;

import org.springframework.stereotype.Component;

import com.cryptosystem.model.Wallet;
import com.cryptosystem.repository.WalletRepository;

@Component
public class WalletServiceImpl implements WalletService {
	private final WalletRepository walletRpository;
	
	public WalletServiceImpl(final WalletRepository walletRpository){
		this.walletRpository = walletRpository;
	}
	
	@Override
	public Wallet getWalletById(String walletId) {
		return this.walletRpository.findByWalletId(walletId);
	}

}
