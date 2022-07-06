package com.zinkworks.atm.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

	private T data;
	private ApiError error;

	public ApiResponse() {
	}

	public ApiResponse(T data, ApiError error) {
		this.data = data;
		this.error = error;
	}

	public synchronized T getData() {
		return data;
	}

	public synchronized void setData(T data) {
		this.data = data;
	}

	public synchronized ApiError getError() {
		return error;
	}

	public synchronized void setError(ApiError error) {
		this.error = error;
	}

}
