package com.cryptosystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptosystem.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
	List<Transaction> findByWalletId(String walletId);
}