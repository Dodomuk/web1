package com.kh.common.exception;

public class CustomException extends RuntimeException{
	public CustomException(String errorCode) {
		super(errorCode);
	}
}
