package com.zinkworks.atm.service.txn.handler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.zinkworks.atm.exception.InsufficientFundsException;
import com.zinkworks.atm.exception.InvalidAmountException;
import com.zinkworks.atm.pojo.AccountBalance;
import com.zinkworks.atm.pojo.BillDetail;
import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.AccountService;
import com.zinkworks.atm.service.util.NoteDispensor;

class WithdrawTransactionHandlerTest {

	@Spy
	@InjectMocks
	WithdrawTransactionHandler withdrawTransactionHandler;

	@Mock
	AccountService accountService;

	@Mock
	NoteDispensor noteDispensor;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldThrowExceptionForInsufficientFunds() {
		AccountBalance balance = new AccountBalance();
		balance.setOpeningBalance(100D);
		balance.setOverdraftBalance(50D);
		doReturn(balance).when(accountService).balance(any());

		Transaction transaction = new Transaction();
		transaction.setAmount(200);

		assertThrows(InsufficientFundsException.class, () -> {
			withdrawTransactionHandler.handle(transaction);
		});
	}

	@Test
	void shouldThrowExceptionForInvalidAmount() {
		AccountBalance balance = new AccountBalance();
		balance.setOpeningBalance(100D);
		balance.setOverdraftBalance(50D);
		doReturn(balance).when(accountService).balance(any());

		Transaction transaction = new Transaction();

		assertThrows(InvalidAmountException.class, () -> {
			withdrawTransactionHandler.handle(transaction);
		});
	}

	@Test
	void shouldHandleWithdrawSuccessfully() {
		AccountBalance balance = new AccountBalance();
		balance.setOpeningBalance(100D);
		balance.setOverdraftBalance(50D);
		doReturn(balance).when(accountService).balance(any());

		Transaction transaction = new Transaction();
		transaction.setAmount(100);

		doReturn(balance).when(accountService).debit(any(), anyInt());

		BillDetail detail = new BillDetail();
		detail.setAmount(50);
		detail.setCount(2);
		List<BillDetail> billDetails = Arrays.asList(detail);
		doReturn(billDetails).when(noteDispensor).dispense(anyInt());

		TransactionResponse response = withdrawTransactionHandler.handle(transaction);
		int amount = response.getBillDetails().get(0).getAmount() * response.getBillDetails().get(0).getCount();
		assertEquals(100, amount);
	}
}
