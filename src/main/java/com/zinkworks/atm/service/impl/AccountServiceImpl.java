package com.zinkworks.atm.service.impl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkworks.atm.db.AccountRepository;
import com.zinkworks.atm.db.entity.Account;
import com.zinkworks.atm.exception.InsufficientFundsException;
import com.zinkworks.atm.exception.UnauthorizedException;
import com.zinkworks.atm.pojo.AccountBalance;
import com.zinkworks.atm.pojo.AccountDetails;
import com.zinkworks.atm.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	AccountTransformer accountTransformer;

	@Override
	public AccountBalance balance(AccountDetails accountDetails) throws UnauthorizedException {
		Account account = accountRepository.findByAccountNumberAndPin(Long.parseLong(accountDetails.getAccountNumber()),
				Integer.parseInt(accountDetails.getPin()));
		if (Objects.nonNull(account)) {
			return accountTransformer.transform(account);
		}

		throw new UnauthorizedException("Account not found with given credentials");
	}

	@Override
	public AccountBalance debit(AccountDetails accountDetails, double amount)
			throws UnauthorizedException, InsufficientFundsException {

		AccountBalance balance = this.balance(accountDetails);

		if (amount > balance.getOpeningBalance() + balance.getOverdraftBalance()) {
			throw new InsufficientFundsException("Account balance is lower than requested amount");
		} else {
			double requestedAmount = amount;
			double openingBalance = balance.getOpeningBalance();
			double overdraftBalance = balance.getOverdraftBalance();

			if (openingBalance > requestedAmount) {
				openingBalance -= requestedAmount;
			} else if (openingBalance < requestedAmount) {
				overdraftBalance -= requestedAmount - openingBalance;
				openingBalance = 0;
			}

			balance.setOpeningBalance(openingBalance);
			balance.setOverdraftBalance(overdraftBalance);
			accountRepository.save(accountTransformer.transform(balance));

			return balance;
		}

	}
}
