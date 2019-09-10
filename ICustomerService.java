package com.nucleus.service;

import java.util.List;

//import org.springframework.stereotype.Service;

import com.nucleus.model.Customer;
import com.nucleus.model.User;

public interface ICustomerService {
	public boolean insert(Customer customer);
	public boolean delete(Customer customer);
	public boolean update(Customer customer,String customerCode);
	public Customer updateCustomer(String customerCode);
	public boolean checkPrimaryKeyViolationCustomer(String customercode);
	public List<Customer> viewAll();
	
}