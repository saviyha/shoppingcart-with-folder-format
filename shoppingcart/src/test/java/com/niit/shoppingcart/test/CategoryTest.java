package com.niit.shoppingcart.test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.model.Category;


public class CategoryTest
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		
		
		
		CategoryDAO categoryDAO=(CategoryDAO)context.getBean("categoryDAO");
		Category category = (Category)context.getBean("category");
		category.setCid("CAT_001");
		category.setCname("TOYOTA");
		category.setCdescription("INDIAN COMPANY");
		
		
		categoryDAO.addCategory (category);
	}
}
