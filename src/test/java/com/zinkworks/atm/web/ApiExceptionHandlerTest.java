package com.zinkworks.atm.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.zinkworks.atm.exception.InsufficientFundsException;
import com.zinkworks.atm.pojo.ApiResponse;

class ApiExceptionHandlerTest {

	ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();

	@Test
	void shouldPrepareErrorResponseCorrectly() {
		InsufficientFundsException ex = new InsufficientFundsException("ERROR MESSAGE");

		ResponseEntity<ApiResponse<Object>> response = apiExceptionHandler.handleException(ex, null);
		assertEquals("1003", response.getBody().getError().getCode());
		assertEquals("ERROR MESSAGE", response.getBody().getError().getMessage());
	}

}
