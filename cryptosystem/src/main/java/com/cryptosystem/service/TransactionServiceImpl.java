package com.cryptosystem.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cryptosystem.exception.InsufficientValueException;
import com.cryptosystem.exception.PriceChangedException;
import com.cryptosystem.model.Balance;
import com.cryptosystem.model.Cryptocurrency;
import com.cryptosystem.model.Customer;
import com.cryptosystem.model.OrderDTO;
import com.cryptosystem.model.Transaction;
import com.cryptosystem.model.UserCryptoCurrencyDTO;
import com.cryptosystem.repository.TransactionRepository;

@Component
public class TransactionServiceImpl implements TransactionService {
	private final TransactionRepository transactionRepository;
	private final CustomerService customerService;
	private final CryptoService cryptoService;
	private final BalanceService balanceService;
	private final static String STABLE_CURRENCY = "USDT";

	public TransactionServiceImpl(final TransactionRepository transactionRepository,
			final CustomerService customerService, final CryptoService cryptoService,
			final BalanceService balanceService, final WalletService walletService) {
		this.transactionRepository = transactionRepository;
		this.customerService = customerService;
		this.cryptoService = cryptoService;
		this.balanceService = balanceService;
	}

	@Override
	public List<Transaction> transactions(String customerId) {
		Customer customer = this.customerService.getCustomerInfo(customerId);
		if(customer == null) {
			List<Transaction> empty = new ArrayList<>();
			return empty;
		}
		return this.transactionRepository.findByWalletId(customer.getWallet().getWalletId());
	}

	@Override
	public void trade(OrderDTO order) {
		Customer customer = this.customerService.getCustomerInfo(order.getCustomerId());
		Cryptocurrency orderCrypto = this.cryptoService.getPriceByCurrencyId(order.getCurrencyId());
		if (order.getOrderType().equals("BUY")) {
			UserCryptoCurrencyDTO remainingUSDT = this.customerService
					.getCryptoByCustomerIdAndCurrencyId(order.getCustomerId(), STABLE_CURRENCY);
			double currentAskingPrice = this.cryptoService.getPriceByCurrencyId(order.getCurrencyId()).getAskPrice();
			double purchaseCost = order.getOrderPrice() * order.getTokenAmount();
			Transaction transaction = new Transaction();
			transaction.setPaymentBy(STABLE_CURRENCY);
			transaction.setTokenAmount(order.getTokenAmount());
			transaction.setTotalCost(purchaseCost);
			transaction.setTradeDate(new Date());
			transaction.setType(order.getOrderType());
			transaction.setWalletId(customer.getWallet().getWalletId());
			transaction.setCrypto(orderCrypto);
			transaction.setTxnId("txn" + new Date().toString());
			transaction.setTransactionId(new Date().toString());
			this.transactionRepository.save(transaction);
			if (order.getOrderPrice() >= currentAskingPrice) {
				if (remainingUSDT.getTokenValue() >= purchaseCost) {
					Balance newBalance = new Balance();
					newBalance.setCrypto(orderCrypto);
					newBalance.setTokenAmount(order.getTokenAmount());
					newBalance.setTokenValue(purchaseCost);
					newBalance.setCrypto(orderCrypto);
					newBalance.setWallet(customer.getWallet());
					newBalance.setId("b" + order.getCurrencyId() + new Date().toString());
					this.balanceService.createNewBalance(newBalance);
					transaction.setStatus("SUCCESS");
					this.transactionRepository.save(transaction);
					Balance changedBalance = customer.getWallet().getWalletBalance().stream()
							.filter(bal -> bal.getCrypto().getCurrencyId().equalsIgnoreCase(this.STABLE_CURRENCY))
							.findAny().orElse(null);
					changedBalance.setTokenAmount(remainingUSDT.getTokenAmount() - purchaseCost);
					changedBalance.setTokenValue(remainingUSDT.getTokenValue() - purchaseCost);
					this.balanceService.updateBalance(changedBalance.getBalanceId(), changedBalance);
				} else {
					transaction.setStatus("FAILED");
					this.transactionRepository.save(transaction);
					throw new InsufficientValueException(order.getCurrencyId());
				}
			} else {
				transaction.setStatus("FAILED");
				this.transactionRepository.save(transaction);
				throw new PriceChangedException(order.getCurrencyId(), currentAskingPrice);
			}
		} else if (order.getOrderType().equals("SELL")) {
			UserCryptoCurrencyDTO remainingBalance = this.customerService
					.getCryptoByCustomerIdAndCurrencyId(order.getCustomerId(), order.getCurrencyId());
			double currentBiddingPrice = this.cryptoService.getPriceByCurrencyId(order.getCurrencyId()).getBidPrice();
			double sellingPrice = order.getOrderPrice() * order.getTokenAmount();
			Transaction transaction = new Transaction();
			transaction.setPaymentBy(order.getCurrencyId());
			transaction.setTokenAmount(order.getTokenAmount());
			transaction.setTotalCost(sellingPrice);
			transaction.setTradeDate(new Date());
			transaction.setType(order.getOrderType());
			transaction.setWalletId(customer.getWallet().getWalletId());
			transaction.setCrypto(orderCrypto);
			transaction.setTxnId("txn" + new Date().toString());
			transaction.setTransactionId(new Date().toString());
			if (order.getOrderPrice() >= currentBiddingPrice) {
				if (remainingBalance.getTokenAmount() >= order.getTokenAmount()) {
					transaction.setStatus("SUCCESS");
					this.transactionRepository.save(transaction);
					Balance changedUSDTBalance = customer.getWallet().getWalletBalance().stream()
							.filter(bal -> bal.getCrypto().getCurrencyId().equalsIgnoreCase(this.STABLE_CURRENCY))
							.findAny().orElse(null);
					changedUSDTBalance.setTokenAmount(changedUSDTBalance.getTokenAmount() + sellingPrice);
					changedUSDTBalance.setTokenValue(changedUSDTBalance.getTokenAmount() + sellingPrice);
					this.balanceService.updateBalance(changedUSDTBalance.getBalanceId(), changedUSDTBalance);
					Balance changedOrderBalance = customer.getWallet().getWalletBalance().stream()
							.filter(bal -> bal.getCrypto().getCurrencyId().equalsIgnoreCase(this.STABLE_CURRENCY))
							.findAny().orElse(null);
					changedOrderBalance.setTokenAmount(changedOrderBalance.getTokenAmount() - order.getTokenAmount());
					changedOrderBalance.setTokenValue(changedOrderBalance.getTokenValue() - sellingPrice);
				} else {
					transaction.setStatus("FAILED");
					this.transactionRepository.save(transaction);
					throw new InsufficientValueException(order.getCurrencyId());
				}
			}else {
				transaction.setStatus("FAILED");
				this.transactionRepository.save(transaction);
				throw new PriceChangedException(order.getCurrencyId(), currentBiddingPrice);
			}
		}
	}
}
