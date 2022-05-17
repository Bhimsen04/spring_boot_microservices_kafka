/**
 * 
 */
package com.weshopify.platform.features.customers.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.weshopify.platform.features.customers.CustomerBean;

/**
 * @author IM-LP-1763
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

//	private static Set<CustomerBean> IN_MEMORY_DB = new HashSet<CustomerBean>();
	private static Map<Integer, CustomerBean> IN_MEMORY_DB = new HashMap<Integer, CustomerBean>();

	/**
	 * if the id is there in the customer bean then update the database with the
	 * data that is coming inside the customer bean else save it as new customer
	 */
	@Override
	public CustomerBean saveCustomer(CustomerBean customerBean) {
		if (customerBean.getCustomerId() != 0)
			IN_MEMORY_DB.put(customerBean.getCustomerId(), customerBean);
		else {
			int customerId = new Random().nextInt();
			customerBean.setCustomerId(customerId);
			IN_MEMORY_DB.put(customerId, customerBean);
		}
		return customerBean;
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customerBean) {
		IN_MEMORY_DB.put(customerBean.getCustomerId(), customerBean);
		return customerBean;
	}

	@Override
	public List<CustomerBean> findAllCustomers() {
		// TODO Auto-generated method stub
		return IN_MEMORY_DB.values().stream().collect(Collectors.toList());
	}

	@Override
	public CustomerBean findCustomerById(int customerId) {
		return IN_MEMORY_DB.get(customerId);
	}

	@Override
	public List<CustomerBean> deleteCustomer(int customerId) {
		IN_MEMORY_DB.remove(customerId);
		return IN_MEMORY_DB.values().stream().collect(Collectors.toList());
	}

	@Override
	public List<CustomerBean> searchCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
