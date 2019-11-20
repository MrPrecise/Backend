package com.myapp.myapp.dao;

import java.util.List;

import com.myapp.myapp.entity.Customer;

public interface CustomerDAO {
	public List<Customer> findAll();

	public Customer getCustomerById(String id);

	public Customer save(Customer theCustomer);
}
