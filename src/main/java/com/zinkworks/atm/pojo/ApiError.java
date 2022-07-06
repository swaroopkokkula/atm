package com.zinkworks.atm.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

	private String code;
	private String message;

	public ApiError() {
	}

	public ApiError(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public synchronized String getCode() {
		return code;
	}

	public synchronized void setCode(String code) {
		this.code = code;
	}

	public synchronized String getMessage() {
		return message;
	}

	public synchronized void setMessage(String message) {
		this.message = message;
	}

}
