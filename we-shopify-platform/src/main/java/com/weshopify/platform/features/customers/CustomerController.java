package com.weshopify.platform.features.customers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.weshopify.platform.features.customers.models.Customer;
import com.weshopify.platform.features.customers.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CustomerController {

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
		int currentPage = 0, NoOfRecPerPage = 5;
		List<CustomerBean> customersList = customerService.findAllCustomers(currentPage, NoOfRecPerPage);
		log.info("customersList : {} ", customersList);
		model.addAttribute("currentPage", currentPage + 1);
		model.addAttribute("NoOfRecPerPage", NoOfRecPerPage);
		model.addAttribute("customersList", customersList);
		return "customer.html";
	}

	@RequestMapping("/view-customers/{currentPage}/{NoOfRecPerPage}")
	public String viewCustomerPageByPagination(Model model, @PathVariable("currentPage") int currentPage,
			@PathVariable("NoOfRecPerPage") int NoOfRecPerPage) {
		log.info("In viewCustomerPageByPagination() method");
		List<CustomerBean> customersList = customerService.findAllCustomers(currentPage, NoOfRecPerPage);
		log.info("customersList : {} ", customersList);
		model.addAttribute("currentPage", currentPage + 1);
		model.addAttribute("NoOfRecPerPage", NoOfRecPerPage);
		model.addAttribute("customersList", customersList);
		return "customer.html";
	}

	@RequestMapping("/delete-customers/{customerId}")
	public String deletCustomer(@PathVariable("customerId") int customerId, Model model) {
		log.info("Deleting Customer {}: ", customerId);
		List<CustomerBean> customersList = customerService.deleteCustomer(customerId);
		log.info("Current CustomerList : {} ", customersList);
		model.addAttribute("customersList", customersList);
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
	public String createCustomer(@ModelAttribute("customer") @Valid CustomerBean customer,
			BindingResult validationResult, Model model) {
		List<String> errorsList = new ArrayList<>();
		log.info("Is customer self registered: {}", customer.isSelfReg());
		log.info("w/o customer ID: {} ", customer.toString());
		if (validationResult.hasErrors()) {
			log.info("Data entered by the user contains the error");
			List<FieldError> fieldErrors = validationResult.getFieldErrors();
			fieldErrors.stream().forEach(fe -> {
				log.error("Field Errors: {} -> {}", fe.getField(), fe.getDefaultMessage());
				errorsList.add(fe.getDefaultMessage());
			});
			/*
			 * commenting this bcz now we are using @ModelAttribute
			 * model.addAttribute("errors", errorsList); model.addAttribute("customer",
			 * customer);
			 */
			return customer.isSelfReg() ? "sign-up.html" : "customer-admin-reg.html";
		}
		customer = customerService.saveCustomer(customer);
		log.info("with customer ID: {} ", customer.toString());
		if (customer.getCustomerId() > 0) {
			String message = "User registration success. Proceed with login";
			model.addAttribute("message", message);
		}
		return customer.isSelfReg() ? "sign-up.html" : "redirect:/view-customers"; // redirecting to the controller
	}

	@RequestMapping(value = {"/search-customers"}, method = RequestMethod.POST)
	public String searchCustomer(@RequestParam("searchKey") String searchKey,
			@RequestParam("searchText") String searchText, Model model) {
		log.info("In searchCustomer() method: ");
		log.info("searchKey:- " + searchKey + " , searchText:- " + searchText);
		List<CustomerBean> customersList = customerService.searchAllCustomers(searchKey, searchText);
		model.addAttribute("customersList", customersList);
		return "customer.html";
	}

}
