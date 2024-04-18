package com.duncancodes.librarymanagement.utils;

import com.duncancodes.librarymanagement.enums.CountryCode;
import com.duncancodes.librarymanagement.enums.Publisher;

import javax.persistence.Entity;
import java.io.Serializable;

public class Isbn implements Serializable {

    private int IsbnCode = 979; //or 978 for books older than 10 years
    private CountryCode countryGroup;
    private Publisher publisher;
    private int TitleEditionFormat;
    private int checkDigit;

    public Isbn() {    }

    public Isbn(CountryCode countryGroup, Publisher publisher, int titleEditionFormat, int checkDigit) {
        this.countryGroup = countryGroup;
        this.publisher = publisher;
        TitleEditionFormat = titleEditionFormat;
        this.checkDigit = checkDigit;
    }

    public int getIsbnCode() {
        return IsbnCode;
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
