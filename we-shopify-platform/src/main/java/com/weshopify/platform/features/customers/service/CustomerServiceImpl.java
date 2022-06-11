/**
 * 
 */
package com.weshopify.platform.features.customers.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.weshopify.platform.features.customers.CustomerBean;
import com.weshopify.platform.features.customers.models.Customer;
import com.weshopify.platform.features.customers.repository.CustomerDataRepo;

/**
 * @author IM-LP-1763
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

//	private static Set<CustomerBean> IN_MEMORY_DB = new HashSet<CustomerBean>();
	private static Map<Integer, CustomerBean> IN_MEMORY_DB = new HashMap<Integer, CustomerBean>();
	private CustomerDataRepo customerRepo;

	@Autowired
	public CustomerServiceImpl(CustomerDataRepo CustomerRepo) {
		this.customerRepo = CustomerRepo;
	}

	/**
	 * if the id is there in the customer bean then update the database with the
	 * data that is coming inside the customer bean else save it as new customer
	 */
	@Override
	public CustomerBean saveCustomer(CustomerBean customerBean) {
//		if (customerBean.getCustomerId() != 0)
//			IN_MEMORY_DB.put(customerBean.getCustomerId(), customerBean);
//		else {
//			int customerId = new Random().nextInt();
//			customerBean.setCustomerId(customerId);
//			IN_MEMORY_DB.put(customerId, customerBean);
//		}

		// convert the bean data to domain to save in database
		Customer customerDomain = new Customer();
		BeanUtils.copyProperties(customerBean, customerDomain);
		customerRepo.save(customerDomain);

		// bcz now customerDomain have auto-generated key
		customerBean.setCustomerId(customerDomain.getCustomerId());
		BeanUtils.copyProperties(customerDomain, customerBean);
		return customerBean;
	}

	@Override
	public CustomerBean updateCustomer(CustomerBean customerBean) {
		// IN_MEMORY_DB.put(customerBean.getCustomerId(), customerBean);
		if (customerBean.getCustomerId() > 0)
			return saveCustomer(customerBean);
		else
			throw new RuntimeException("Updating customer applied on existing customers only");
	}

	@Override
	public List<CustomerBean> findAllCustomers() {
		// TODO Auto-generated method stub
		List<CustomerBean> customerList = new ArrayList<CustomerBean>();
		Iterable<Customer> customIterator = customerRepo.findAll();
		customIterator.forEach(customer -> {
			CustomerBean customerBean = new CustomerBean();
			BeanUtils.copyProperties(customer, customerBean);
			customerList.add(customerBean);

		});
		// return IN_MEMORY_DB.values().stream().collect(Collectors.toList());
		return customerList;
	}

	@Override
	public List<CustomerBean> findAllCustomers(int currentPage, int noOfRecPerPage) {
		Pageable page = PageRequest.of(currentPage, noOfRecPerPage);
		Page<Customer> customerPages = customerRepo.findAll(page);
		List<Customer> customerDomainList = customerPages.getContent();

		if (customerDomainList != null && !customerDomainList.isEmpty()) {
			List<CustomerBean> customerList = new ArrayList<>();
			customerDomainList.stream().forEach(customer -> {
				CustomerBean customerBean = new CustomerBean();
				BeanUtils.copyProperties(customer, customerBean);
				customerList.add(customerBean);
			});
			return customerList;
		}
		return null;
	}

	@Override
	public CustomerBean findCustomerById(int customerId) {
		// return IN_MEMORY_DB.get(customerId);
		CustomerBean customerBean = new CustomerBean();
		customerRepo.findById(customerId).ifPresentOrElse((customer) -> {
			BeanUtils.copyProperties(customer, customerBean);
		}, () -> {
			throw new RuntimeException("No Customer Found with the CustomerId:\t" + customerId);
		});
		return customerBean;

	}

	@Override
	public List<CustomerBean> deleteCustomer(int customerId) {
		// IN_MEMORY_DB.remove(customerId);
		customerRepo.deleteById(customerId);
		return findAllCustomers();
	}

	@Override
	public List<CustomerBean> searchCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

}
