package com.weshopify.platform.features.customers;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.weshopify.platform.features.customers.commons.EmailDomainValid;
import com.weshopify.platform.features.customers.commons.PasswordValidator;

import lombok.Data;

@Data
public class CustomerBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3392255258797133035L;
	
	private int customerId;
	private boolean selfReg;

	@NotEmpty(message = "first name shouldn't be empty")
	private String firstName;

	@NotEmpty(message = "last name shouldn't be empty")
	private String lastName;

	@NotEmpty(message = "user name shouldn't be empty")
	private String userName;

	@NotEmpty(message = "email shouldn't be empty")
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	@EmailDomainValid(message = "email domain is not valid, please enter valid domain")
	private String email;

	@Pattern(regexp = "^([+]\\d{2})?\\d{10}$", message = "Mobile Number should be of minimum 10 digits")
	private String mobileNumber;

	@NotEmpty(message = "password shouldn't be empty")
	@PasswordValidator(message = "Password should be 8 characters Length with the "
			+ "One Letter must be Capital and One Number Should Present")
	private String password;

}
