package com.zinkworks.atm.service.txn.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zinkworks.atm.exception.InsufficientFundsException;
import com.zinkworks.atm.exception.InvalidAmountException;
import com.zinkworks.atm.pojo.AccountBalance;
import com.zinkworks.atm.pojo.AccountDetails;
import com.zinkworks.atm.pojo.BillDetail;
import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.AccountService;
import com.zinkworks.atm.service.util.NoteDispensor;

@Component
public class WithdrawTransactionHandler implements TransactionHandler {

	@Autowired
	AccountService accountService;

	@Autowired
	NoteDispensor noteDispensor;

	@Override
	public TransactionResponse handle(Transaction transaction) {
		AccountDetails accountDetails = transaction.getAccountDetails();

		AccountBalance balance = accountService.balance(accountDetails);

		TransactionResponse response = new TransactionResponse();
		if (transaction.getAmount() > balance.getOpeningBalance() + balance.getOverdraftBalance()) {
			throw new InsufficientFundsException("Account balance is lower than requested amount");
		} else if(transaction.getAmount() <= 0) {
			throw new InvalidAmountException("Requested amount is invalid");
		}

		response.setRemainingBalance(accountService.debit(accountDetails, transaction.getAmount()));

		List<BillDetail> billDetails = noteDispensor.dispense(transaction.getAmount());
		response.setBillDetails(billDetails);

		return response;
	}

}
