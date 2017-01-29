package com.niit.shoppingcart.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.shoppingcart.model.Category;

@EnableTransactionManagement
@SuppressWarnings("deprecation")
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO 
{
	@Autowired
	private SessionFactory sessionFactory; 
	
	public CategoryDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	
	
	@Transactional
	public void addCategory(Category category)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(category);
	}
	
	@Transactional
	public void delete(String id)
	{
		Category category=new Category();
		category.setCid(id);
		sessionFactory.getCurrentSession().delete(category);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Category getCategory(String id)
	{
		String hql="from Category where cID="+"'"+id+"'";
		@SuppressWarnings("rawtypes")
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Category> list=(List<Category>) query.list();
		if(list!=null&& !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
		
	}
	
	@Transactional
	public Category getByName(String name)
	{
		String hql="from Category where cname="+"'"+ name +"'";
		@SuppressWarnings("rawtypes")
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Category> list= query.list();
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Category> list()
	{
		List<Category> list= (List<Category>) sessionFactory.getCurrentSession().createCriteria(Category.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
				
	}
}

