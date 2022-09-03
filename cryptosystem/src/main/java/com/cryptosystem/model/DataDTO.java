package com.cryptosystem.model;

import java.util.List;

public class DataDTO {
	private List<HuobiDTO> data; 
	
	public List<HuobiDTO> getData() {
		return data;
	}
	public void setData(List<HuobiDTO> huobiList) {
		this.data = huobiList;
	}
}
