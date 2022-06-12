package com.weshopify.platform.features.customers.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.weshopify.platform.features.customers.models.Customer;

//public interface CustomerDataRepo extends CrudRepository<Customer, Integer>{
//public interface CustomerDataRepo extends PagingAndSortingRepository<Customer, Integer> {
public interface CustomerDataRepo extends JpaRepository<Customer, Integer> {

	@Query("from Customer c where c.email like %?1%")
	public List<Customer> searchByEmail(@Param("email") String email);

	@Query("from Customer c where c.mobileNumber like %:mobileNumber%")
	public List<Customer> searchByMobileNumber(@Param("mobileNumber") String mobileNumber);

	@Query("from Customer c where c.userName like %?1%")
	public List<Customer> searchByUserName(@Param("userName") String userName);

	@Query("from Customer c where :searchCustomer like %:searchText%")
	public List<Customer> searchCustomer(@Param("searchCustomer") String searchCustomer,
			@Param("searchText") String searchText);

}
