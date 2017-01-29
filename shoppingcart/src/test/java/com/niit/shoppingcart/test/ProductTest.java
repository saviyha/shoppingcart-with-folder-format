package com.niit.shoppingcart.test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.model.Product;
public class ProductTest
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		
		
		
		
		ProductDAO productDAO=(ProductDAO)context.getBean("productDAO");
		Product product= (Product)context.getBean("product");
		product.setPid("PRO_001");
		product.setPname("car");
		product.setPprise(500000);
		product.setPdescription("INDIAN COMPANY");
		
		
		productDAO.addProduct (product);
	}
}
