package com.zinkworks.atm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.zinkworks.atm.db.AccountRepository;
import com.zinkworks.atm.db.entity.Account;
import com.zinkworks.atm.exception.InsufficientFundsException;
import com.zinkworks.atm.exception.UnauthorizedException;
import com.zinkworks.atm.pojo.AccountBalance;
import com.zinkworks.atm.pojo.AccountDetails;

class AccountServiceImplTest {

	@Spy
	@InjectMocks
	AccountServiceImpl accountServiceImpl;

	@Mock
	AccountRepository accountRepository;

	@Spy
	AccountTransformer accountTransformer;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void shouldReturnAccountBalanceProvidedValidCredentials() {
		Account account = new Account();
		account.setOpeningBalance(100);
		doReturn(account).when(accountRepository).findByAccountNumberAndPin(anyLong(), anyInt());

		assertEquals(100, accountServiceImpl.balance(accountDetails()).getOpeningBalance());
	}

	@Test
	void shouldThrowExceptionIfInValidCredentialsAreProvided() {
		doReturn(null).when(accountRepository).findByAccountNumberAndPin(anyLong(), anyInt());

		UnauthorizedException ex = assertThrows(UnauthorizedException.class, () -> {
			accountServiceImpl.balance(accountDetails());
		});
		assertEquals("Account not found with given credentials", ex.getMessage());
	}

	@Test
	void debitShouldThrowInsufficientFundsException() {
		AccountBalance balance = balance();
		balance.setOpeningBalance(100D);
		balance.setOverdraftBalance(10D);
		doReturn(balance).when(accountServiceImpl).balance(any());

		InsufficientFundsException ex = assertThrows(InsufficientFundsException.class, () -> {
			accountServiceImpl.debit(accountDetails(), 200D);
		});
		assertEquals("Account balance is lower than requested amount", ex.getMessage());
	}

	@Test
	void shouldDebitRequestedAmountFromOpeningBalance() {
		AccountBalance balance = balance();
		balance.setOpeningBalance(100D);
		balance.setOverdraftBalance(10D);
		doReturn(balance).when(accountServiceImpl).balance(any());

		AccountBalance response = accountServiceImpl.debit(accountDetails(), 80D);
		assertEquals(20D, response.getOpeningBalance());
		assertEquals(10D, response.getOverdraftBalance());
	}

	@Test
	void shouldDebitRequestedAmountFromOpeningBalanceAndOverdraftBalance() {
		AccountBalance balance = balance();
		balance.setOpeningBalance(100D);
		balance.setOverdraftBalance(20D);
		doReturn(balance).when(accountServiceImpl).balance(any());

		AccountBalance response = accountServiceImpl.debit(accountDetails(), 110D);
		assertEquals(0D, response.getOpeningBalance());
		assertEquals(10D, response.getOverdraftBalance());
	}

	private AccountDetails accountDetails() {
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setAccountNumber("1234");
		accountDetails.setPin("11");
		return accountDetails;
	}

	private AccountBalance balance() {
		AccountBalance balance = new AccountBalance();
		balance.setAccountDetails(accountDetails());
		return balance;
	}
}
