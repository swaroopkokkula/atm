package com.zinkworks.atm.service.txn.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zinkworks.atm.pojo.Transaction;

@Component
public class TransactionHandlerFactory {

	@Autowired
	GetBalanceTransactionHandler getBalanceHandler;

	@Autowired
	WithdrawTransactionHandler withdrawHandler;

	public TransactionHandler getHandler(Transaction.TransactionType type) {
		if (type.equals(Transaction.TransactionType.BALANCE)) {
			return getBalanceHandler;
		} else if (type.equals(Transaction.TransactionType.WITHDRAW)) {
			return withdrawHandler;
		}

		throw new RuntimeException("Can't process the request");
	}
}
