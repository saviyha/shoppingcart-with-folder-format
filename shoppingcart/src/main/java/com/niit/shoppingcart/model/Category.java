package com.niit.shoppingcart.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Category
{
	@Id
	private String cid;
	private String cname;
	private String cdescription;
	private Set<Product> products;
	
	@OneToMany(mappedBy="category",fetch=FetchType.EAGER)
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	@Id
	public String getCid() 
	{
		return cid;
	}
	public void setCid(String cid)
	{
		this.cid = cid;
	}
	public String getCname()
	{
		return cname;
	}
	public void setCname(String cname)
	{
		this.cname = cname;
	}
	public String getCdescription()
	{
		return cdescription;
	}
	public void setCdescription(String cdescription)
	{
		this.cdescription = cdescription;
	}

	
	
}
