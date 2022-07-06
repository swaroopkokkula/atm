package com.zinkworks.atm.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BillDetail {

	private int amount;
	private int count;

	public BillDetail() {
	}

	public BillDetail(int amount, int count) {
		this.amount = amount;
		this.count = count;
	}

	public void decrementCount(int count) {
		this.count -= count;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
