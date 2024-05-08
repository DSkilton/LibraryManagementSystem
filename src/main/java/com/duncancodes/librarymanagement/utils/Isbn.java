package com.duncancodes.librarymanagement.utils;

import com.duncancodes.librarymanagement.enums.CountryCode;
import com.duncancodes.librarymanagement.enums.Publisher;

import java.io.Serializable;

public class Isbn implements Serializable {

	private Long IsbnCode = 979l; //or 978 for books older than 10 years
	private CountryCode countryGroup;
	private Publisher publisher;
	private int TitleEditionFormat;
	private int checkDigit;

	public Isbn() {	}

	public Isbn(CountryCode countryGroup, Publisher publisher, int titleEditionFormat, int checkDigit) {
		this.countryGroup = countryGroup;
		this.publisher = publisher;
		TitleEditionFormat = titleEditionFormat;
		this.checkDigit = checkDigit;
	}

	public Long getIsbnCode() {
		return IsbnCode;
	}

	public void setIsbnCode(Long isbnCode) {
		IsbnCode = isbnCode;
	}

	public CountryCode getCountryGroup() {
		return countryGroup;
	}

	public void setCountryGroup(CountryCode countryGroup) {
		this.countryGroup = countryGroup;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public int getTitleEditionFormat() {
		return TitleEditionFormat;
	}

	public void setTitleEditionFormat(int titleEditionFormat) {
		TitleEditionFormat = titleEditionFormat;
	}

	public int getCheckDigit() {
		return checkDigit;
	}

	public void setCheckDigit(int checkDigit) {
		this.checkDigit = checkDigit;
	}
}
