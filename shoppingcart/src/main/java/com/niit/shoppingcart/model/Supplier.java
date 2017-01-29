package com.niit.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Supplier
{
	@Id
	private String sid;
	private String sname;
	
	private String sphone;
	private String saddress;
	public String getSid()
	{
		return sid;
	}
	public void setSid(String sid) 
	{
		this.sid = sid;
	}
	public String getSname()
	{
		return sname;
	}
	public void setSname(String sname) 
	{
		this.sname = sname;
	}
	public String getSphone()
	{
		return sphone;
	}
	public void setSphone(String sphone) 
	{
		this.sphone = sphone;
	}
	
	public String getSaddress()
	{
		return saddress;
	}
	public void setSaddress(String saddress)
	{
		this.saddress = saddress;
	}
}
