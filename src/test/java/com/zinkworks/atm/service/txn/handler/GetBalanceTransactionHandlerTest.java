package com.zinkworks.atm.service.txn.handler;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.zinkworks.atm.pojo.AccountBalance;
import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.AccountService;

class GetBalanceTransactionHandlerTest {

	@Spy
	@InjectMocks
	GetBalanceTransactionHandler getBalanceTransactionHandler;

	@Mock
	AccountService accountService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldReturnUserBalance() {
		doReturn(new AccountBalance()).when(accountService).balance(any());

		TransactionResponse response = getBalanceTransactionHandler.handle(new Transaction());
		assertNotNull(response);
		assertNotNull(response.getRemainingBalance());
	}

}
