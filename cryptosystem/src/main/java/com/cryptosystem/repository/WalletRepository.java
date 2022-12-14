package com.cryptosystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptosystem.model.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
	Wallet findByWalletId(String walletId);
}
