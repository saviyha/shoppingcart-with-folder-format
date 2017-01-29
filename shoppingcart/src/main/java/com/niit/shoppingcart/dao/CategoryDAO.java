package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.model.Category;


public interface CategoryDAO 
{
	public void addCategory(Category category);
	public Category getCategory(String id);
	public void delete(String id);
	public List<Category> list();
	public Category getByName(String name);


}