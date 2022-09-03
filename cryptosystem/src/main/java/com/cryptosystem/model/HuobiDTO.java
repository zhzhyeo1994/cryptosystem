package com.cryptosystem.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HuobiDTO {	
	private String symbol;
	private double bid;
	private double bidSize;
	private double ask;
	private double askSize;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getBid() {
		return bid;
	}
	public void setBid(double bid) {
		this.bid = bid;
	}
	public double getBidSize() {
		return bidSize;
	}
	public void setBidSize(double bidSize) {
		this.bidSize = bidSize;
	}
	public double getAsk() {
		return ask;
	}
	public void setAsk(double ask) {
		this.ask = ask;
	}
	public double getAskSize() {
		return askSize;
	}
	public void setAskSize(double askSize) {
		this.askSize = askSize;
	}
}
