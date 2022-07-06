package com.zinkworks.atm.exception;

public class UnauthorizedException extends RuntimeException implements ApiException {

	private static final long serialVersionUID = 1L;

	private final String code = "1001";

	public UnauthorizedException() {
	}

	public UnauthorizedException(String message) {
		super(message);
	}

	@Override
	public String getCode() {
		return this.code;
	}
}
