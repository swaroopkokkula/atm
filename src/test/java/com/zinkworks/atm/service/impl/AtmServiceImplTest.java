package com.zinkworks.atm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.txn.handler.TransactionHandler;
import com.zinkworks.atm.service.txn.handler.TransactionHandlerFactory;

class AtmServiceImplTest {

	@Spy
	@InjectMocks
	AtmServiceImpl atmServiceImpl;

	@Mock
	TransactionHandlerFactory transactionHandlerFactory;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldHandleTransaction() {
		TransactionHandler handler = mock(TransactionHandler.class);
		doReturn(handler).when(transactionHandlerFactory).getHandler(any());

		TransactionResponse expResponse = new TransactionResponse();
		doReturn(expResponse).when(handler).handle(any());

		assertEquals(expResponse, atmServiceImpl.handleTransaction(new Transaction()));
	}

}
