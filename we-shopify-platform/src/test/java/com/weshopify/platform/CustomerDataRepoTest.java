package com.weshopify.platform;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.weshopify.platform.features.customers.models.Customer;
import com.weshopify.platform.features.customers.repository.CustomerDataRepo;

public class CustomerDataRepoTest extends WeShopifyPlatformApplicationTests {

	@Autowired
	private CustomerDataRepo customerDataRepo;

	@Test
	public void customerCreation() {
		Customer customer = new Customer();
		customer.setEmail("bhimsen1304@gmail.com");
		customer.setFirstName("Bhimsen");
		customer.setLastName("Garg");
		customer.setMobileNumber("9478256841");
		customer.setPassword("testUser@123");
		customer.setSelfReg(true);
		customer.setUserName("bhimsenGarg");
		customer = customerDataRepo.save(customer);
		System.out.println("Customer ID: " + customer.getCustomerId());
		assertNotNull(customer.getCustomerId()); // if it is not null, then we can say test cases will pass
	}

}
