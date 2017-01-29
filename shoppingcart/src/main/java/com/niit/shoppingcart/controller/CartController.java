package com.niit.shoppingcart.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.Cart;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.User;

@Controller
public class CartController 
{
	
// @Autowired
// Cart cart;
// 
 @Autowired
 private CartDAO cartDAO;
 
 @Autowired
 private CategoryDAO categoryDAO;
 
 @Autowired
 private ProductDAO productDAO;
 
// @Autowired
// private Product product;
// 
// @Autowired
// private UserDAO userDAO;
//	
// 	@Autowired
// 	 private User user;
//   
//   
	@RequestMapping(value = "/myCart", method = RequestMethod.GET)
	public String myCart(Model model,Principal principal)
	{
		try
		{
		model.addAttribute("cart",new Cart());
		model.addAttribute("cartList",this.cartDAO.userCartList(principal.getName()));
		model.addAttribute("category",new Category());
		model.addAttribute("categoryList",this.categoryDAO.list());
		model.addAttribute("displayCart","true");
		
		
		int i,j=0;
		int s=0;
		int n=cartDAO.userCartList(principal.getName()).size();
		System.out.println(n);
		for(i=0;i<n;i++)
		{
			s=s+cartDAO.userCartList(principal.getName()).get(i).getPrice();
		}
		System.out.print(s);
		model.addAttribute("sum",s);
		
		}
		catch(Exception ex)
		{
			System.out.println("ex.getMessage");
		}
		return "cart";
		
		
	}
	
	@RequestMapping(value = "cart/add/{pid}", method = RequestMethod.GET)
	public String addToCart(@PathVariable("pid") String id, HttpServletRequest request ,Principal principal)
	{
		try
		{
		
		Product product=productDAO.getProduct(id);
		//User user = userDAO.getUser(id);
		Cart cart=new Cart();   		
		cart.setPrice(product.getPprise());
		cart.setP_id(product.getPid());
		cart.setP_name(product.getPname());
		cart.setQuantity(1);
		cart.setU_id(principal.getName());
		cart.setStatus("N");
		
		cartDAO.addCart(cart);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return "redirect:/";
		
		
		
	}
	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String checkout(Model model,Principal principal)
	{
		int i,j=0;
		int s=0;
		int n=cartDAO.userCartList(principal.getName()).size();
		System.out.println(n);
		for(i=0;i<n;i++)
		{
			s=s+cartDAO.userCartList(principal.getName()).get(i).getPrice();
		}
		System.out.print(s);
		model.addAttribute("sum",s);
		model.addAttribute("cart",new Cart());
		model.addAttribute("cartList",this.cartDAO.userCartList(principal.getName()));
		
		return "/checkout";
	    
	}
	@RequestMapping("/cart/delete/{id}")
	public String deleteCart(@PathVariable("id") int id,Model model)
	{
		cartDAO.deleteCart(id);
		return "redirect:/myCart";
	}
	
	
	
	@RequestMapping("/payment")
	public String getPayment()
	{
		return "payment";
	}
	
	@RequestMapping("/thanks")
	public String getThanks()
	{
		return "thanks";
	}
	
}
