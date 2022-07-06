package com.zinkworks.atm.service.txn.handler;

import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;

public interface TransactionHandler {

	public TransactionResponse handle(Transaction transaction);
}
