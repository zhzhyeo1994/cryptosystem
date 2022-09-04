package com.cryptosystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptosystem.model.Balance;

public interface BalanceRepository extends JpaRepository<Balance, String> {
	Balance findByBalanceId(String balanceId);
}
