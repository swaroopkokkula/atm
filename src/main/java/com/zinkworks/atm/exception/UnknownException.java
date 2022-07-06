package com.zinkworks.atm.exception;

public class UnknownException extends RuntimeException implements ApiException {

	private static final long serialVersionUID = 1L;

	private final String code = "1000";

	public UnknownException() {
	}

	public UnknownException(String message) {
		super(message);
	}

	public UnknownException(Throwable ex) {
		super(ex);
	}

	@Override
	public String getCode() {
		return this.code;
	}
}
