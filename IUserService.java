package com.nucleus.service;

import java.util.List;

import com.nucleus.model.Role;
import com.nucleus.model.User;

public interface IUserService {

	public void saveUser(User user, String[] role);
	public void addRole(Role role);
	public boolean checkPrimaryKeyViolationUser(String roleid);
	public boolean checkUser(String userid);
	public List<String> getRoles(String userid);
}
