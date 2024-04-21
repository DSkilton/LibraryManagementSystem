package com.duncancodes.librarymanagement.utils;

import javax.persistence.Embeddable;

@Embeddable
public class Contact {
	private String mobile;
	private String email;

	public Contact() {	}

	public Contact(String mobile, String email) {
		this.mobile = mobile;
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
