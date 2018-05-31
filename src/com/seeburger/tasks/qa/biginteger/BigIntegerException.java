package com.seeburger.tasks.qa.biginteger;

@SuppressWarnings("serial")
public abstract class BigIntegerException extends RuntimeException {

	BigIntegerException(String msg) {
		super(msg);
	}

}