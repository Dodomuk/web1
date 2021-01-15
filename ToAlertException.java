package com.kh.common.exception;

public class ToAlertException extends CustomException{
	private static final long serialVersionUID = -3007270838782253360L;
	public ToAlertException(String errorCode) {
		super(errorCode);
	}
}
