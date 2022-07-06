package com.zinkworks.atm.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Transaction {

	private AccountDetails accountDetails;
	private TransactionType txnType;
	private int amount;

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	public TransactionType getTxnType() {
		return txnType;
	}

	public void setTxnType(TransactionType txnType) {
		this.txnType = txnType;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public enum TransactionType {
		BALANCE, WITHDRAW;
	}
}
