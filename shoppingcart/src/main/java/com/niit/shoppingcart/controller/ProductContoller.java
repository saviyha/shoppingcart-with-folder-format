package com.niit.shoppingcart.controller;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;
import com.niit.shoppingcart.util.Util;


@Controller
public class ProductContoller
{
	@Autowired
    Product product;
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
    CategoryDAO categoryDAO;
	@Autowired
	Category category;
	
	@Autowired
    SupplierDAO supplierDAO;
	@Autowired
	Supplier supplier;
	
	private Path path;
	
	
	@RequestMapping("/product")
	public ModelAndView getProduct(Model m)
	{
		
		m.addAttribute("product",new Product());
		m.addAttribute("category",new Category());
		m.addAttribute("supplier",new Supplier());
		
		
		
		m.addAttribute("productList", this.productDAO.list());
		m.addAttribute("categoryList", this.categoryDAO.list());
		m.addAttribute("supplierList", this.supplierDAO.list());
		
		
		ModelAndView model= new ModelAndView("product");
		return model;
	}
	@RequestMapping(value="product/add", method=RequestMethod.POST)
	public String addProduct(Model model, @ModelAttribute("product")Product product,HttpServletRequest request)
	{
		category=categoryDAO.getByName(product.getCategory().getCname());
		System.out.println(category.getCname());
		
		supplier=supplierDAO.getByName(product.getSupplier().getSname());
		System.out.println(supplier.getSname());
		
		product.setCategory(category);
		product.setSupplier(supplier);
		
		product.setCategory_id(category.getCid());
		product.setSupplier_id(supplier.getSid());
		
		Util util = new Util();
		String st = util.removeComma(product.getPid());
		product.setPid(st);
		
		
		
		productDAO.addProduct(product);
		
		MultipartFile file = product.getImage();
		System.out.println(product.getImage());
		String rootDirectory=request.getSession().getServletContext().getRealPath("/");
		path =Paths.get(rootDirectory + "\\WEB-INF\\resources\\images\\"+product.getPid()+".jpg");
		if(file!=null && !file.isEmpty())
		{
			try{
				file.transferTo(new File(path.toString()));
				System.out.println("image uploaded....");
			}
		catch(Exception e)
		{
		e.printStackTrace();
		//throw new RuntimeException("image saving failed",e);
		}
		}
		return "redirect:/product";
		
		
	}
	
	@RequestMapping("product/remove/{pid}")
	public String deleteProduct(@PathVariable("pid") String id, ModelMap model) throws Exception {

		try {
			productDAO.delete(id);
			model.addAttribute("message", "Successfully Deleted");
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
		}
		// redirectAttrs.addFlashAttribute(arg0, arg1)
		return "redirect:/product";
	}
	

	@RequestMapping("product/edit/{pid}")
	
	public String editProduct(@PathVariable("pid") String id, Model model) {
		System.out.println("editProduct");
		model.addAttribute("product", this.productDAO.getProduct(id));
		model.addAttribute("productList", this.productDAO.list());
		model.addAttribute("categoryList", this.categoryDAO.list());
		model.addAttribute("supplierList", this.supplierDAO.list());
		
		
		return "product";
	}
	
	@RequestMapping(value="product/get/{pid}")
	public String getSelectedProduct(@PathVariable("pid") String id,Model model,RedirectAttributes redirectAttributes)
	{
	redirectAttributes.addFlashAttribute("selectedProduct",productDAO.getProduct(id));
	return "redirect:/backToHome";
	}

	@RequestMapping(value="/backToHome",method=RequestMethod.GET)
	public String backToHome(@ModelAttribute("selectedProduct")
	final Product selectedProduct,final Model model)
	{
	model.addAttribute("selectedProduct",selectedProduct);
	model.addAttribute("categoryList",this.categoryDAO.list());
	return "item";
	}
}

	

