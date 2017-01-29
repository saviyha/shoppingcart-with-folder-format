package com.niit.shoppingcart.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Supplier;



public class SupplierTest  
{
	@SuppressWarnings("resource")
	public static void main(String[] args)
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();
		
		
		SupplierDAO supplierDAO=(SupplierDAO)context.getBean("supplierDAO");
		Supplier supplier= (Supplier) context.getBean("supplier");
		supplier.setSid("SUP_001");
		supplier.setSname("car");
		supplier.setSphone("436472378");
		supplier.setSaddress("mvm");
		
		supplierDAO.addSupplier(supplier);
	}
}
