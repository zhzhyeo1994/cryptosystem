package com.cryptosystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cryptosystem.model.Cryptocurrency;

@Repository
public interface CryptoRepository  extends JpaRepository<Cryptocurrency, String> {
	Cryptocurrency findByCurrencyId(String symbol);
}
