package com.cryptosystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.cryptosystem.model.BinanceDTO;
import com.cryptosystem.model.Cryptocurrency;
import com.cryptosystem.model.DataDTO;
import com.cryptosystem.model.HuobiDTO;
import com.cryptosystem.repository.CryptoRepository;

@Component
public class CryptoServiceImpl implements CryptoService {
	private final RestTemplate restTemplate;
	private final CryptoRepository cryptoRepository;
	private final String binancePricesURL;
	private final String huobiPricesURL;
	private static final String BTC_USDT = "BTCUSDT";
	private static final String ETH_USDT = "ETHUSDT";

	public CryptoServiceImpl(final RestTemplate restTemplate, final CryptoRepository cryptoRepository,
			final @Value("${external.api.binance.url}") String binancePricesURL,
			final @Value("${external.api.huobi.url}") String huobiPricesURL) {
		this.restTemplate = restTemplate;
		this.cryptoRepository = cryptoRepository;
		this.binancePricesURL = binancePricesURL;
		this.huobiPricesURL = huobiPricesURL;
	}

	@Override
	public Cryptocurrency updatePrice(String symbol, double bestBidPrice, double bestAskPrice) {
		Cryptocurrency toUpdate = this.cryptoRepository.findByCurrencyId(symbol);
		toUpdate.setBidPrice(bestBidPrice);
		toUpdate.setAskPrice(bestAskPrice);
		return this.cryptoRepository.save(toUpdate);
	}
	
	@Override
	public List<Cryptocurrency> getPrices() {
		return this.cryptoRepository.findAll();
	}

	@Override
	public Cryptocurrency getPriceByCurrencyId(String currencyId) {
		return this.cryptoRepository.findByCurrencyId(currencyId.toUpperCase());
	}

	@Override
	public void retrieveAndUpdateLatestPrice() {
		List<BinanceDTO> binancePrices = new ArrayList<>();
		List<HuobiDTO> huobiPrices = new ArrayList<>();
		ResponseEntity<List<BinanceDTO>> binanceResponse = restTemplate.exchange(this.binancePricesURL, HttpMethod.GET,
				null, new ParameterizedTypeReference<List<BinanceDTO>>() {
				});
		ResponseEntity<DataDTO> huobiResponse = restTemplate.exchange(this.huobiPricesURL, HttpMethod.GET, null,
				new ParameterizedTypeReference<DataDTO>() {
				});

		if (binanceResponse != null && binanceResponse.hasBody()) {
			binancePrices = binanceResponse.getBody().stream()
					.filter(currency -> ETH_USDT.equalsIgnoreCase(currency.getSymbol())
							|| BTC_USDT.equalsIgnoreCase(currency.getSymbol()))
					.collect(Collectors.toList());
		}

		if (huobiResponse != null && huobiResponse.hasBody()) {
			huobiPrices = huobiResponse.getBody().getData().stream()
					.filter(currency -> ETH_USDT.equalsIgnoreCase(currency.getSymbol())
							|| BTC_USDT.equalsIgnoreCase(currency.getSymbol()))
					.collect(Collectors.toList());
		}
		for (HuobiDTO hPrice : huobiPrices) {
			for (BinanceDTO bPrice : binancePrices) {
				if (hPrice.getSymbol().equalsIgnoreCase(bPrice.getSymbol())) {
					try {
						double bPriceInDouble = Double.parseDouble(bPrice.getBidPrice());
						double hPriceInDouble = hPrice.getBid();
						this.updatePrice(bPrice.getSymbol(),
								hPriceInDouble >= bPriceInDouble ? hPriceInDouble : bPriceInDouble,
								hPriceInDouble >= bPriceInDouble ? hPriceInDouble : bPriceInDouble);
					} catch (NumberFormatException e) {
						this.updatePrice(bPrice.getSymbol(), hPrice.getBid(), hPrice.getAsk()).getAskPrice();
					}

				}
			}
		}
	}
}
