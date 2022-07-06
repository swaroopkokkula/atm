package com.zinkworks.atm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zinkworks.atm.exception.UnknownException;
import com.zinkworks.atm.pojo.ApiResponse;
import com.zinkworks.atm.pojo.Transaction;
import com.zinkworks.atm.pojo.TransactionResponse;
import com.zinkworks.atm.service.AtmService;

@RestController
public class AtmController {

	@Autowired
	AtmService atmService;

	@PostMapping("/transactions")
	public ResponseEntity<ApiResponse<TransactionResponse>> handleTransaction(@RequestBody Transaction transaction) {

		try {
			TransactionResponse response = atmService.handleTransaction(transaction);

			return ResponseEntity.ok().body(new ApiResponse<>(response, null));
		} catch(Exception ex) {
			throw new UnknownException(ex);
		}
		
	}
}
