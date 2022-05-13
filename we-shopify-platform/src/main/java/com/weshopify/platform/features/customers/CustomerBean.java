package com.weshopify.platform.features.customers;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestParam;

public class CustomerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3392255258797133035L;
	private String userName;
	private String email;
	private String mobileNumber;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "CustomerBean [userName=" + userName + ", email=" + email + ", mobileNumber=" + mobileNumber
				+ ", password=" + password + "]";
	}

}
