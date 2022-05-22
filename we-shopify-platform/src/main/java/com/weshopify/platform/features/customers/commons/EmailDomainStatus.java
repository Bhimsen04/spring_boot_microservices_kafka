package com.weshopify.platform.features.customers.commons;

public enum EmailDomainStatus {

	UNDELIVERABLE("UNDELIVERABLE"), DELIVERABLE("DELIVERABLE");

	private String emailDeliveryStatus;

	private EmailDomainStatus(String emailDeliveryStatus) {
		this.emailDeliveryStatus = emailDeliveryStatus;
	}

	public String getEmailDeliveryStatus() {
		return this.emailDeliveryStatus;
	}

}
