package com.zinkworks.atm.service.txn.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zinkworks.atm.pojo.AccountDetails;
import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.AccountService;

@Component
public class GetBalanceTransactionHandler implements TransactionHandler {

	@Autowired
	AccountService accountService;

	@Override
	public TransactionResponse handle(Transaction transaction) {
		AccountDetails accountDetails = transaction.getAccountDetails();

		TransactionResponse response = new TransactionResponse();
		response.setRemainingBalance(accountService.balance(accountDetails));
		return response;
	}

}
