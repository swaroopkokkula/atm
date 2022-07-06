package com.zinkworks.atm.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.zinkworks.atm.exception.ApiException;
import com.zinkworks.atm.exception.InsufficientFundsException;
import com.zinkworks.atm.exception.InvalidAmountException;
import com.zinkworks.atm.exception.UnauthorizedException;
import com.zinkworks.atm.pojo.ApiError;
import com.zinkworks.atm.pojo.ApiResponse;

@ControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(value = { UnauthorizedException.class, InvalidAmountException.class,
			InsufficientFundsException.class })
	protected ResponseEntity<ApiResponse<Object>> handleException(ApiException ex, WebRequest request) {
		ApiError error = new ApiError();
		error.setCode(ex.getCode());
		error.setMessage(ex.getMessage());
		ApiResponse<Object> apiResponse = new ApiResponse<>();
		apiResponse.setError(error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
}
