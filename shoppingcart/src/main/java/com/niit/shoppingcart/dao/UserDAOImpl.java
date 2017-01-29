package com.niit.shoppingcart.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.User;



@Repository("userDAO")
@EnableTransactionManagement
public class UserDAOImpl implements UserDAO 
{
	@Autowired
	private SessionFactory sessionfactory;
	
	public UserDAOImpl(SessionFactory sessionfactory)
	
	{
		this.sessionfactory=sessionfactory;
		
	}

	@Transactional 
	public void addUser(User user)
	{
		sessionfactory.getCurrentSession().saveOrUpdate(user);
	}
	
}