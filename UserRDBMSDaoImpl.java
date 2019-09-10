package com.nucleus.dao;

import java.util.ArrayList;
import java.util.List;



import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nucleus.model.Customer;
import com.nucleus.model.Role;
import com.nucleus.model.User;

@Repository
public class UserRDBMSDaoImpl implements IUserDao {

	@Autowired
	SessionFactory sessionfactory;//session factory bean injected 
	
	/*----------------------saves the user object in the database ------------------*/
	@Override
		public boolean saveUser(User user)
	{
		Session session=sessionfactory.getCurrentSession();
		session.persist(user);
		return true;
	}
	
	/*----------------------retrieves the role id from role table for a particular role name----------------------*/
	@Override
	public String retrieveRoleId(String rolename) {
		Query q=(Query)sessionfactory.getCurrentSession().createQuery("select roleid from Role where rolename=?");
		q.setParameter(0, rolename);
		List<String> list=q.list();
		return list.get(0);
			}
	
	/*----------------------adds a new role for the user using hibernate------------------*/

	@Override
	public boolean addrole(Role role) {
		Session session=sessionfactory.getCurrentSession();
		session.persist(role);
		return true;
	}
	/*----------------------checks for primary key violation for role id using hibernate------------------*/
	public boolean checkPrimaryKeyViolationUser(String roleid)
	{
		Session session=sessionfactory.getCurrentSession();
		Query query=(Query) session.createQuery("from Role where roleid=?");
		query.setParameter(0, roleid);
		List<Customer> list=query.list();
		if(list.isEmpty())
			return false;
		else
		return true;
	}
	/*----------------------checks for primary key violation for userid if it already exists or not using hibernate------------------*/
	@Override
	public boolean checkUser(String userid) {
		Session session=sessionfactory.getCurrentSession();
		Query query=(Query) session.createQuery("from User where userid=?");
		query.setParameter(0,userid);
		List<Customer> list=query.list();
		if(list.isEmpty())
			return false;
		else
		return true;
	}
	/*----------------------this method get the roles of user incase of multiple roles------------------*/
	@Override
	public List<String> getRoles(String userid) {
		Session session=sessionfactory.getCurrentSession();
		Query query=session.createSQLQuery("select r.rolename from role1010 r join user10_role10 ur where r.roleid=ur.role_id and ur.user_id=?");
		query.setParameter(0,userid);
		List<String> lis=query.list();
		return lis;
	}

}
