package com.zinkworks.atm.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.zinkworks.atm.db.entity.Account;
import com.zinkworks.atm.pojo.AccountBalance;
import com.zinkworks.atm.pojo.AccountDetails;

@Component
public class AccountTransformer {

	public AccountBalance transform(Account account) {
		AccountBalance accountBalance = null;
		if (Objects.nonNull(account)) {
			accountBalance = new AccountBalance();
			AccountDetails accountDetails = new AccountDetails();
			accountDetails.setAccountNumber(String.valueOf(account.getAccountNumber()));
			accountDetails.setPin(String.valueOf(account.getPin()));
			accountBalance.setAccountDetails(accountDetails);
			accountBalance.setOpeningBalance(account.getOpeningBalance());
			accountBalance.setOverdraftBalance(account.getOverdraftBalance());
		}

		return accountBalance;
	}

	public Account transform(AccountBalance accountBalance) {
		Account account = null;
		if (Objects.nonNull(accountBalance)) {
			account = new Account();
			account.setAccountNumber(Long.parseLong(accountBalance.getAccountDetails().getAccountNumber()));
			account.setPin(Integer.parseInt(accountBalance.getAccountDetails().getPin()));
			account.setOpeningBalance(accountBalance.getOpeningBalance());
			account.setOverdraftBalance(accountBalance.getOverdraftBalance());
		}

		return account;
	}
}
