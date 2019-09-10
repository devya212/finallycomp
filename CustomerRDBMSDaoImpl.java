package com.nucleus.dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nucleus.model.Customer;
@Repository
public class CustomerRDBMSDaoImpl implements ICustomerDao  {

	@Autowired
	SessionFactory sessionFactory; //session factory dependency added
		
		
	/*----------------------inserts the customer object in the database using hibernate------------------*/
		@Override
	public boolean insert(Customer customer) {
		Session session=sessionFactory.getCurrentSession();
		   session.persist(customer);
			return true;
		
		
	}
		/*----------------------deletes the customer data from the database on basis of customer code primary key using hibernate------------------*/
	@Override
	public boolean delete(Customer customer) {
		Session session=sessionFactory.getCurrentSession();
		   session.delete(customer);
			return true;
		
	}
	/*----------------------fetches the customer object in the database on the basis of customer code for updation purpose primary key using hibernate------------------*/
	@Override
	public Customer updateCustomer(String customerCode) {
		Session session=sessionFactory.getCurrentSession();
		   Customer customer=(Customer)session.get(Customer.class,customerCode);
			return customer;
		
	}
	/*----------------------updates the customer object in the database on the basis of customer code primary key using hibernate------------------*/
	@Override
	public boolean update(Customer customer, String customerCode) {
		Session session=sessionFactory.getCurrentSession();
		  session.update(customer);
			return true;
	}
	/*----------------------gets the  list of customer from the database using hql query------------------*/
	@Override
	public List<Customer> viewAll() {
		
		Session session=sessionFactory.getCurrentSession();
		  List<Customer> list=session.createQuery("from Customer").list();
			return list;
	}
	
	/*----------------------checks primary key violation in customer table while insertion ------------------*/
	
	public boolean checkPrimaryKeyViolationCustomer(String customercode)
	{
		Session session=sessionFactory.getCurrentSession();
		Query query=(Query) session.createQuery("from Customer where customerCode=?");
		query.setParameter(0, customercode);
		List<Customer> list=query.list();
		if(list.isEmpty())
			return false;
		else
		return true;
	}
}