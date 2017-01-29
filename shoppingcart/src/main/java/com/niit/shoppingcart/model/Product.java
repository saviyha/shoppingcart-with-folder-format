package com.niit.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Component
public class Product
{
	@Id
	private String pid;
	private String pname;
	private int pprise;
	private String pdescription;
	
	@ManyToOne
	@JoinColumn(name="category_id",insertable=false,updatable=false,nullable=false)
	private Category category;
	private String category_id;
	
	@ManyToOne
	@JoinColumn(name="supplier_id",insertable=false,updatable=false,nullable=false)
	private Supplier supplier;
	private String supplier_id;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public Supplier getSupplier() {
		return supplier;
	}
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	public String getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
	
	public String getPid() 
	{
		return pid;
	}
	public void setPid(String pid) 
	{
		this.pid = pid;
	}
	public String getPname() 
	{
		return pname;
	}
	public void setPname(String pname) 
	{
		this.pname = pname;
	}
	public int getPprise() 
	{
		return pprise;
	}
	public void setPprise(int pprise) 
	{
		this.pprise = pprise;
	}
	public String getPdescription()
	{
		return pdescription;
	}
	public void setPdescription(String pdescription) 
	{
		this.pdescription = pdescription;
	}
	
	
    @Transient
	private MultipartFile image;
	
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
}

