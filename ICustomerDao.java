package com.nucleus.dao;

import java.util.List;

import com.nucleus.model.Customer;
import com.nucleus.model.User;

public interface ICustomerDao {
	public boolean insert(Customer customer);
	public boolean delete(Customer customer);
	public boolean update(Customer customer,String customerCode);
	public Customer updateCustomer(String customerCode);
	public boolean checkPrimaryKeyViolationCustomer(String customercode);
	public List<Customer> viewAll();
}