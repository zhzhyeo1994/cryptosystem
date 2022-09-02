package com.cryptosystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cryptosystem.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

}