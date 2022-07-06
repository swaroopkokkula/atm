package com.zinkworks.atm.exception;

public class InsufficientFundsException extends RuntimeException implements ApiException {

	private static final long serialVersionUID = 1L;

	private final String code = "1003";

	public InsufficientFundsException() {
	}

	public InsufficientFundsException(String message) {
		super(message);
	}

	@Override
	public String getCode() {
		return this.code;
	}
}
