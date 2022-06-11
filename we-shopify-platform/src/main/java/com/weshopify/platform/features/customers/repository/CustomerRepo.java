package com.weshopify.platform.features.customers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weshopify.platform.features.customers.models.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
