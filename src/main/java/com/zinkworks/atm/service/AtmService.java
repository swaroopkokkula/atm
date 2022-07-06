package com.zinkworks.atm.service;

import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;

public interface AtmService {

	public TransactionResponse handleTransaction(Transaction transaction);
}
