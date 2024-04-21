package com.duncancodes.librarymanagement.enums;

import java.util.concurrent.ThreadLocalRandom;

public enum CountryCode {
//	https://everything2.com/title/ISBN+Country+codes
	ENGLAND(0),
	France(1);

	private final int code;

	private CountryCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static CountryCode getRandomCountryCode(){
		return values()[ThreadLocalRandom.current().nextInt(values().length)];
	}
}
