package com.weshopify.platform.features.customers.commons;

public enum CustomerSearchOptions {

	// name: BY_EMAIL , key: By Email, value: email

	BY_EMAIL("By Email", "email"), BY_UserName("By UserName", "userName"), BY_Mobile("By Mobile", "mobileNumber");

	public String key;
	private String value;

	CustomerSearchOptions(String searchKey, String searchValue) {
		this.key = searchKey;
		this.value = searchValue;
	}

	public static String getSearchName(String searchKey) {
		for (CustomerSearchOptions customerOption : CustomerSearchOptions.values()) {
			if (customerOption.key.equalsIgnoreCase(searchKey))
				return customerOption.value;
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(CustomerSearchOptions.BY_EMAIL.name());
		System.out.println(CustomerSearchOptions.valueOf(CustomerSearchOptions.BY_EMAIL.name()).key);
		System.out.println(CustomerSearchOptions.valueOf(CustomerSearchOptions.BY_EMAIL.name()).value);
	}
}
