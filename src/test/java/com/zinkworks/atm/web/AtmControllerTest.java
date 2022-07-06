package com.zinkworks.atm.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zinkworks.atm.db.AccountRepository;
import com.zinkworks.atm.pojo.AccountBalance;
import com.zinkworks.atm.pojo.AccountDetails;
import com.zinkworks.atm.pojo.BillDetail;
import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.AtmService;

@WebMvcTest(AtmController.class)
public class AtmControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AtmService atmService;

	@MockBean
	private AccountRepository accountRepository;

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void getBalanceResponseShouldContainOpeningBalance() throws Exception {
		setupGetBalanceTest();
		double expected = 200D;

		this.mockMvc
				.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(getRequest())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.remainingBalance.openingBalance").value(expected));
	}

	@Test
	public void getBalanceResponseShouldContainOverdraftBalance() throws Exception {
		setupGetBalanceTest();
		double expected = 100D;

		this.mockMvc
				.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(getRequest())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.remainingBalance.overdraftBalance").value(expected));
	}

	@Test
	public void withdrawResponseShouldContainBillDetails() throws Exception {
		setupWithdrawTest();
		int expected = 50;

		this.mockMvc
				.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(getRequest())))
				.andExpect(status().isOk()).andExpect(jsonPath("$.data.billDetails[0].amount").value(expected));
	}

	private Transaction getRequest() {
		Transaction transaction = new Transaction();
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setAccountNumber("1234");
		accountDetails.setPin("1111");
		transaction.setAccountDetails(accountDetails);
		return transaction;
	}

	private void setupGetBalanceTest() {
		TransactionResponse response = new TransactionResponse();
		AccountBalance balance = new AccountBalance();
		balance.setAccountDetails(getRequest().getAccountDetails());
		balance.setOpeningBalance(200D);
		balance.setOverdraftBalance(100D);
		response.setRemainingBalance(balance);

		doReturn(response).when(atmService).handleTransaction(any(Transaction.class));
	}

	private void setupWithdrawTest() {
		TransactionResponse response = new TransactionResponse();
		List<BillDetail> billDetails = Arrays.asList(new BillDetail(50, 10));
		response.setBillDetails(billDetails);

		doReturn(response).when(atmService).handleTransaction(any(Transaction.class));
	}
}
