package com.nucleus.dao;

import java.util.List;

import com.nucleus.model.Role;
import com.nucleus.model.User;

public interface IUserDao {

	public boolean saveUser(User user);
	public String retrieveRoleId(String rolename);
	public boolean addrole(Role role);
	public boolean checkPrimaryKeyViolationUser(String roleid);
	public boolean checkUser(String userid);
	public List<String> getRoles(String userid);
}
