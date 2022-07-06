package com.zinkworks.atm.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionResponse {

	private List<BillDetail> billDetails;
	private AccountBalance remainingBalance;

	public synchronized List<BillDetail> getBillDetails() {
		return billDetails;
	}

	public synchronized void setBillDetails(List<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}

	public synchronized AccountBalance getRemainingBalance() {
		return remainingBalance;
	}

	public synchronized void setRemainingBalance(AccountBalance remainingBalance) {
		this.remainingBalance = remainingBalance;
	}

}
