package com.niit.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.util.Util;

@Controller
public class CategoryController
{
	@Autowired
	Category category;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping("/category")
	public ModelAndView getCategory(Model m)
	{
		m.addAttribute("category",new Category());
		ModelAndView model= new ModelAndView("category");
		return model;
	}
	@RequestMapping(value="category/add", method=RequestMethod.POST)
	public String addCategory(Model model, @ModelAttribute("category")Category category)
	{
		Util util = new Util();
		String st = util.removeComma(category.getCid());
		category.setCid(st);
		
		categoryDAO.addCategory(category);
		return "redirect:/category";
	}
	
	@RequestMapping("category/remove/{cid}")
	public String deleteCategory(@PathVariable("cid") String id, ModelMap model) throws Exception {

		try {
			categoryDAO.delete(id);
			model.addAttribute("message", "Successfully Deleted");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		// redirectAttrs.addFlashAttribute(arg0, arg1)
		return "redirect:/category";
	}
	

	@RequestMapping("category/edit/{cid}")
	public String editCategory(@PathVariable("cid") String id, Model model) {
		System.out.println("editCategory");
		model.addAttribute("category", this.categoryDAO.getCategory(id));
		model.addAttribute("categoryList", this.categoryDAO.list());
		return "category";
	}
	
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
		public String listCategories(Model model){
			model.addAttribute("category", category);
			model.addAttribute("categoryList",this.categoryDAO.list());
			return "category";
		
	}
	
}
