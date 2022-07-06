package com.zinkworks.atm.service.txn.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.zinkworks.atm.pojo.Transaction;

class TransactionHandlerFactoryTest {

	@Spy
	@InjectMocks
	TransactionHandlerFactory transactionHandlerFactory;

	@Mock
	GetBalanceTransactionHandler getBalanceHandler;

	@Mock
	WithdrawTransactionHandler withdrawHandler;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldReturnBalanceTransactionHandler() {
		assertEquals(getBalanceHandler, transactionHandlerFactory.getHandler(Transaction.TransactionType.BALANCE));
	}

	@Test
	void shouldReturnWithdrawTransactionHandler() {
		assertEquals(withdrawHandler, transactionHandlerFactory.getHandler(Transaction.TransactionType.WITHDRAW));
	}

}
