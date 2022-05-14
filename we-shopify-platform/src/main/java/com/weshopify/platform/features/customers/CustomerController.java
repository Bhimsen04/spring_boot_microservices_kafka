package com.weshopify.platform.features.customers;

import java.util.Arrays;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weshopify.platform.features.customers.service.CustomerService;

@Controller
public class CustomerController {

	Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customer")
	public String renderSignupPage() {
		log.info("In render signup page method");
		return "sign-up.html";
	}

	@RequestMapping("/view-customers")
	public String viewCustomerPage() {
		log.info("In view customer page method");
		return "customer.html";
	}
	
	/*
	 * @RequestMapping(value = {"/customer"} , method = RequestMethod.POST) public
	 * String createCustomer(@RequestParam String userName , @RequestParam String
	 * email , @RequestParam String mobileNumber,
	 * 
	 * @RequestParam String password) {
	 * System.out.println("--------------Customer Registration-----------");
	 * System.out.println(userName); System.out.println(email);
	 * System.out.println(mobileNumber); System.out.println(password); return
	 * "sign-up.html"; }
	 */

	@RequestMapping(value = { "/customer" }, method = RequestMethod.POST)
	public String createCustomer(CustomerBean customer, Model model) {
		log.info("--------------Create Customer Registration-----------");
		log.info(customer.toString());
		customer = customerService.saveCustomer(customer);
		if (customer.getCustomerId() > 0) {
			String message = "User registration success. Proceed with login";
			model.addAttribute("message", message);
		}
		return "sign-up.html";
	}

}
