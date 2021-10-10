package com.bithumb.common.response;

public enum SuccessCode {

	INTEREST_FINDALL_SUCCESS("FIND ALL INTEREST SUCCESS"),
	INTEREST_CREATE_SUCCESS("CREATE INTEREST SUCCESS"),
	INTEREST_DELETE_SUCCESS("DELETE INTEREST SUCCESS");

	private final String message;

	SuccessCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
