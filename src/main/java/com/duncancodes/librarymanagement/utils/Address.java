package com.duncancodes.librarymanagement.utils;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String address1;
	private String address2;
	private String county;
	private String postcode;

	public Address() { 	}

	public Address(String address1, String address2, String county, String postcode) {
		this.address1 = address1;
		this.address2 = address2;
		this.county = county;
		this.postcode = postcode;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
}
