package com.zinkworks.atm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.AtmService;
import com.zinkworks.atm.service.txn.handler.TransactionHandler;
import com.zinkworks.atm.service.txn.handler.TransactionHandlerFactory;

@Service
public class AtmServiceImpl implements AtmService {

	@Autowired
	TransactionHandlerFactory transactionHandlerFactory;

	@Override
	public TransactionResponse handleTransaction(Transaction transaction) {
		TransactionHandler handler = transactionHandlerFactory.getHandler(transaction.getTxnType());
		return handler.handle(transaction);
	}

}
