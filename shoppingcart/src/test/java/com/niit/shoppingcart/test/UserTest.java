package com.niit.shoppingcart.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.User;

public class UserTest  
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		
	
		UserDAO userDAO=(UserDAO)context.getBean("userDAO");
		User user = (User) context.getBean("user");
		user.setName("veena");
		user.setPassword("12345");
		user.setAddress("mvm");
		user.setEmail("veenachida");
		user.setPhonenumber("5347384");
		
		userDAO.addUser(user);
	}
}
