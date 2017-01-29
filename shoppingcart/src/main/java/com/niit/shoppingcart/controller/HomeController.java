package com.niit.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.User;

@Controller
public class HomeController
{
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	User user;

	@Autowired
	Category category;
	
	@Autowired
	CategoryDAO categoryDAO;

	
	@RequestMapping("/")
	public String getLanding(Model model)
	{
		model.addAttribute("category", category);
		model.addAttribute("categoryList",this.categoryDAO.list());
		return "index";
	}
	@RequestMapping("/home")
	public String getHome(Model model)
	{
		model.addAttribute("category", category);
		model.addAttribute("categoryList",this.categoryDAO.list());
		return "index";
	}
	@RequestMapping("/register")
	public ModelAndView getRegister(Model m)
	{
		m.addAttribute("user",new User());
		ModelAndView model= new ModelAndView("register");
		return model;
	}
	@RequestMapping(value="register/add", method=RequestMethod.POST)
	public String addUser(Model model, @ModelAttribute("user")User user)
	{
		user.setRole("ROLE_USER");
		userDAO.addUser(user);
		return "redirect:/";
	}
	@RequestMapping("/admin")
	public String getAdmin()
	{
		return "admin";
	}
	@RequestMapping(value="/user")
	public String getUser()
	{
		return "login";
	}
	@RequestMapping(value="/login")
	public String  login(@RequestParam(value="error",required=false)String error,@RequestParam(value="logout",required=false) String logout,Model model)
	{
		if(error!=null)
		{
			System.out.println("Error..");
			model.addAttribute("loginerror","...Invalid username and password");
		}
		
		if(logout!=null)
		{
			System.out.println("logout called..");
			model.addAttribute("loginmsg","...you have been logged out");
		}
		
		return "login";
	}
	
}

	