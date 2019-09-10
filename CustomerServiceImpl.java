package com.nucleus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.nucleus.dao.CustomerRDBMSDaoImpl;
import com.nucleus.dao.ICustomerDao;
import com.nucleus.model.Customer;
import com.nucleus.model.User;
@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	ICustomerDao icdao;
	boolean check;
	List<Customer> list;
	Customer customer;
	
	/*----------------------service layer for customer entity------------------*/
	/*----------------------calls customer dao layer insert method------------------*/
	@Transactional
	public boolean insert(Customer customer)
	{
		check=icdao.insert(customer);
		return check;
		
	}
	
	/*----------------------calls customer dao layer update method------------------*/
	
	@Transactional
	public boolean update(Customer customer,String customerCode)
	{
	       check=icdao.update(customer,customerCode);
		return check;
		
	}
	
	/*----------------------calls customer dao layer delete method------------------*/
	
	@Transactional
	public boolean delete(Customer customer) {
		check=icdao.delete(customer);
		return check;
	}
   
	/*---------------calls customer dao layer updatecustomer method for getting the customer details for updation purpose------------*/
	
	@Transactional
	public Customer updateCustomer(String customerCode) {
		customer=icdao.updateCustomer(customerCode);
		return customer;
	}

	/*----------------------calls customer dao layer view method to view details of customer------------------*/
	@Transactional
	public List<Customer> viewAll() {
		list=icdao.viewAll();
		return list;
	}
	
	/*----------------------calls customer dao layer for primary key validation check------------------*/
	
	@Transactional
	public boolean checkPrimaryKeyViolationCustomer(String customercode) {
		
		check=icdao.checkPrimaryKeyViolationCustomer(customercode);
		return check;
	
	}
	
		
}