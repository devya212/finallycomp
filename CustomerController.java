package com.nucleus.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nucleus.model.Customer;
import com.nucleus.service.ICustomerService;
import com.nucleus.utility.PasswordEncode;


/*------------customer controller--------------------------*/

@Controller
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	@Autowired
	PasswordEncode passwordencode;
	Date dateobj = new Date();
	
	/*----------------------request mapping for customer details add form-------------------*/
	@RequestMapping("/userRegisterCustomer")	
	public ModelAndView request1(@ModelAttribute("customer2") Customer customer2)
	{
		
		ModelAndView model=new ModelAndView();
		List<Customer> lis=customerService.viewAll();
		model.addObject("list",lis);
		model.setViewName("add");
		return model;
	}
	/*----------------------request mapping for customer details delete form-------------------*/
	@RequestMapping("/userDeleteCustomer")	
	public ModelAndView request2(@ModelAttribute("customer2") Customer customer2)
	{
		ModelAndView model=new ModelAndView();
		List<Customer> lis=customerService.viewAll();
		model.addObject("list",lis);
		model.setViewName("delete");
		return model;
	}
	/*----------------------request mapping for customer details update form taking input as customer code-------------------*/
	@RequestMapping("/userUpdateCustomer")	
	public ModelAndView request3(@ModelAttribute("customer2") Customer customer2)
	{
		ModelAndView model=new ModelAndView();
		List<Customer> lis=customerService.viewAll();
		model.addObject("list",lis);
		model.setViewName("updateform");
		return model;
		
	}
	/*----------------------request mapping for viewing all customer data-------------------*/
	@RequestMapping("/userviewall")	
	public ModelAndView request10(@ModelAttribute("customer2") Customer customer2)
	{
		
		List<Customer> lis=customerService.viewAll();
		
		return new ModelAndView("view2","list",lis);
	}
	/*---------------------- saving customer details in database-------------------*/
	@RequestMapping("/usersaveCustomer")
	public ModelAndView request4(@ModelAttribute("customer2") @Valid Customer customer,Principal principal,BindingResult result)
	{
		if(result.hasErrors())
		{
			
			return new ModelAndView("add");
		}
		if(customerService.checkPrimaryKeyViolationCustomer(customer.getCustomerCode())==true)
		{
			return new ModelAndView("add","errmsg","customer code already exists");
		}	
		
        customer.setCreateDate(dateobj);
        customer.setCreatedBy(principal.getName());
    
        
		customerService.insert(customer);
		ModelAndView model=new ModelAndView();
		List<Customer> lis=customerService.viewAll();
		model.addObject("list",lis);
		model.addObject("addmsg","customer details added successfully");
		model.setViewName("add");
		return  model;
	}
	
	
	/*----------------------deleting customer details on the basis of customer code-------------------*/
	@RequestMapping("/userdeleteCustomer")
	public ModelAndView request5(@ModelAttribute("customer2") @Valid Customer customer, BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("delete");
		}
		
		customerService.delete(customer);
		List<Customer> lis=customerService.viewAll();
		model.addAttribute("list",lis);
		return new ModelAndView("delete","delmsg","customer details deleted successfully");
	}
	/*----------------------request mapping for customer details update form for getting customer code-------------------*/
	@RequestMapping("/userupdateCustomer")
	public ModelAndView request6(@ModelAttribute("customer2") Customer customer, BindingResult result)
	{
		if(customerService.checkPrimaryKeyViolationCustomer(customer.getCustomerCode())==false)
		{
			return new ModelAndView("updateform","errmsg","customer code does not exist");
		}
	   Customer customer1=customerService.updateCustomer(customer.getCustomerCode());
	   ModelAndView model=new ModelAndView();
		
	   java.util.Date createDate=customer1.getCreateDate();
	   
	   if(createDate!=null)
	   {
		   java.sql.Date dt=new java.sql.Date(createDate.getTime());
		   model.addObject("crdate",dt); 
	   }
	   else
	   {
		   model.addObject("crdate",(Date) createDate); 
	   }
	   List<Customer> lis=customerService.viewAll();
	   model.addObject("list",lis);
		model.addObject("crby", customer1.getCreatedBy());
		model.addObject("customer2",customer1);
		model.setViewName("update");
		return model;
		
		
	}
	/*----------------------updating customer details on the basis of customer code-------------------*/
 @RequestMapping("/userUpdateCustomer1")
	public ModelAndView request7(@ModelAttribute("customer2") Customer customer,Principal principal, BindingResult result,Model model)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("update");
		}
		 customer.setModifiedDate(dateobj);
		 List<Customer> lis=customerService.viewAll();
			model.addAttribute("list",lis);
		customerService.update(customer,customer.getCustomerCode());
		return new ModelAndView("makerHome","updatemsg","customer details updated successfully");
	}
  
 }