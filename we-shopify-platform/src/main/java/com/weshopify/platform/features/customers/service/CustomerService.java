package com.weshopify.platform.features.customers.service;

import java.util.List;

import com.weshopify.platform.features.customers.CustomerBean;

public interface CustomerService {

	public CustomerBean saveCustomer(CustomerBean customerBean);

	public CustomerBean updateCustomer(CustomerBean customerBean);

	public List<CustomerBean> findAllCustomers();

	public CustomerBean findCustomerById(int customerId);

	public List<CustomerBean> deleteCustomer(int customerId);

	// filter Parameters
	public List<CustomerBean> searchCustomer();

	public List<CustomerBean> findAllCustomers(int currentPage, int noOfRecPerPage);

	public List<CustomerBean> searchAllCustomers(String searchKey, String searchText);

}
