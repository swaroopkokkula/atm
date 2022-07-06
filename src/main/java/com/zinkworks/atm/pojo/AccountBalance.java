package com.zinkworks.atm.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountBalance {

	private AccountDetails accountDetails;
	private Double openingBalance;
	private Double overdraftBalance;
	private Double maxWithDrawableBalance;

	public AccountDetails getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(AccountDetails accountDetails) {
		this.accountDetails = accountDetails;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Double getOverdraftBalance() {
		return overdraftBalance;
	}

	public void setOverdraftBalance(Double overdraftBalance) {
		this.overdraftBalance = overdraftBalance;
	}

	public Double getMaxWithDrawableBalance() {
		return maxWithDrawableBalance;
	}

	public void setMaxWithDrawableBalance(Double maxWithDrawableBalance) {
		this.maxWithDrawableBalance = maxWithDrawableBalance;
	}

}
