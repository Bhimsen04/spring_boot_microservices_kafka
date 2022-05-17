package com.weshopify.platform.features.customers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.weshopify.platform.features.customers.service.CustomerService;

@Controller
public class CustomerController {

	Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customer")
	public String customerSelfSignupPage() {
		log.info("In customerSelfSignupPage() method");
		return "sign-up.html";
	}

	@RequestMapping("/customer-admin-reg")
	public String customerAdminSignupPage(Model model) {
		log.info("In customerAdminSignupPage() method");
		model.addAttribute("customer", new CustomerBean());
		return "customer-admin-reg.html";
	}

	@RequestMapping("/view-customers")
	public String viewCustomerPage(Model model) {
		log.info("In viewCustomerPage() method");
		List<CustomerBean> customerList = customerService.findAllCustomers();
		log.info("CustomerList : {} ", customerList);
		model.addAttribute("customerList", customerList);
		return "customer.html";
	}

	@RequestMapping("/delete-customers/{customerId}")
	public String deletCustomer(@PathVariable("customerId") int customerId, Model model) {
		log.info("Deleting Customer {}: ", customerId);
		List<CustomerBean> customerList = customerService.deleteCustomer(customerId);
		log.info("Current CustomerList : {} ", customerList);
		model.addAttribute("customerList", customerList);
		return "customer.html";
	}

	@RequestMapping(value = { "/edit-customers/{customerId}" }, method = RequestMethod.GET)
	public String editCustomer(@PathVariable("customerId") int customerId, Model model) {
		log.info("customer ID in Edit Customer Page:\t" + customerId);
		CustomerBean customer = customerService.findCustomerById(customerId);
		model.addAttribute("customer", customer);
		return "customer-admin-reg.html";

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
		log.info("Is customer self registered: {}", customer.isSelfReg());
		log.info("w/o customer ID: {} ", customer.toString());
		customer = customerService.saveCustomer(customer);
		log.info("with customer ID: {} ", customer.toString());
		if (customer.getCustomerId() > 0) {
			String message = "User registration success. Proceed with login";
			model.addAttribute("message", message);
		}
		if (customer.isSelfReg())
			return "sign-up.html";
		return "redirect:/view-customers"; // redirecting to the controller
	}

}
