package com.weshopify.platform.features.customers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

	@RequestMapping("/customer")
	public String renderSignupPage() {
		// model.addAttribute("name" , "bhimsen");
		return "sign-up.html";
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
	public String createCustomer(CustomerBean customer) {
		System.out.println("--------------Customer Registration-----------");
		System.out.println(customer);
		return "sign-up.html";
	}
}
