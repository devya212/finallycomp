package com.nucleus.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nucleus.model.Role;
import com.nucleus.model.User;
import com.nucleus.service.IUserService;



@Controller
public class UserController {

	@Autowired
	IUserService userService;
	/*----------------------admin fills in the details of user along with role in the registration page-------------------*/
	@RequestMapping("/adminregister")
	 public ModelAndView request1(User user)
	 {
		 return new ModelAndView("adminRegistration");
	 }

	/*----------------------admin adds a new user to the system-------------------*/
	@RequestMapping("/adminsaveUser")	
	public ModelAndView request2(@ModelAttribute("user") User user,@RequestParam("role0") String[] role)
	{
		if(userService.checkUser(user.getUserid())==true)
		{
			return new ModelAndView("adminRegistration","errmsg","user id already exists");
			
		}
		userService.saveUser(user,role);
		return new ModelAndView("result4","user",user);
	}
	
	/*----------------------add new role form page -------------------*/
	
	@RequestMapping("/adminaddRole")	
	public ModelAndView request3(Role role)
	{
		
		return new ModelAndView("addrole");
	}
	
	/*----------------------admin inserts a new role to the system-------------------*/
	
	
	@RequestMapping("/admininsertRole")	
	public ModelAndView request2(@ModelAttribute("role") Role role)
	{
		if(userService.checkPrimaryKeyViolationUser(role.getRoleid())==true)
			return new ModelAndView("addrole","errmsg","role id already exists");
		
		userService.addRole(role);
		return new ModelAndView("result4","mesg","saved successfully");
	}


}
