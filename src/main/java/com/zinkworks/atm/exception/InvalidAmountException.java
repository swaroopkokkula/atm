package com.zinkworks.atm.exception;

public class InvalidAmountException extends RuntimeException implements ApiException {

	private static final long serialVersionUID = 1L;

	private final String code = "1002";

	public InvalidAmountException() {
	}

	public InvalidAmountException(String message) {
		super(message);
	}

	@Override
	public String getCode() {
		return this.code;
	}
}
