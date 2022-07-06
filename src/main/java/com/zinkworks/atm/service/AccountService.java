package com.zinkworks.atm.service;

import com.zinkworks.atm.pojo.AccountDetails;
import com.zinkworks.atm.exception.InsufficientFundsException;
import com.zinkworks.atm.exception.UnauthorizedException;
import com.zinkworks.atm.pojo.AccountBalance;

public interface AccountService {

	public AccountBalance balance(AccountDetails accountDetails) throws UnauthorizedException;

	public AccountBalance debit(AccountDetails accountDetails, double amount)
			throws UnauthorizedException, InsufficientFundsException;
	
//	public AccountBalance debit(AccountDetails accountDetails, int amount)
//			throws UnauthorizedException, InsufficientFundsException;
}
