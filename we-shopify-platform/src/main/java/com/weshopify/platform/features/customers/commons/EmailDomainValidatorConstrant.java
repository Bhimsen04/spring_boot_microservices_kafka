package com.weshopify.platform.features.customers.commons;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopify.platform.features.customers.service.CustomerServicesUtil;

public class EmailDomainValidatorConstrant implements ConstraintValidator<EmailDomainValid, String> {

	private static final Logger logger = LoggerFactory.getLogger(EmailDomainValidatorConstrant.class);

	@Autowired
	private CustomerServicesUtil customerServicesUtil;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		logger.info("Inside the email domain Validate method");
		return customerServicesUtil.isValidEmailDomain(value);
	}

}
