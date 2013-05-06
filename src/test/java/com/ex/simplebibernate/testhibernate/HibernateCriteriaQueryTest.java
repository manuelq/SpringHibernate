package com.ex.simplebibernate.testhibernate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.simplehibernate.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class HibernateCriteriaQueryTest {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session session;

	private Long userId;

    
    @Before
    public final void setup(){
    	session=sessionFactory.getCurrentSession();
    	Product product = new Product();
    	product.setPrice(new BigDecimal(123.4));
    	product.setTax(new BigDecimal(23.4));
    	session.persist(product);
    	product = new Product();
    	product.setPrice(new BigDecimal(223.4));
    	product.setTax(new BigDecimal(123.4));
    	session.persist(product);
    }
    
    @Test
    @Transactional
    public final void testFindProductQuery(){
    	List<Product> results = session.createQuery("select p from Product p order by price + tax desc").list();
    	Assert.assertEquals(new BigDecimal(346.8).setScale(1,RoundingMode.HALF_UP).doubleValue(), results.get(0).getCalculatedValue());
    }
    
    
    @Test
    @Transactional
    public final void testFindProductQuery2(){
    	DetachedCriteria query = DetachedCriteria.forClass(Product.class);
    	query.addOrder(Order.desc("calculatedValue"));
    	List<Product> results = query.getExecutableCriteria(session).setMaxResults(100).list();
    	Assert.assertEquals(new BigDecimal(346.8).setScale(1,RoundingMode.HALF_UP).doubleValue(), results.get(0).getCalculatedValue());
    }
    
    
    @Test
    @Transactional
    public final void testFindPersonQuery3(){
    	List<Product> results = session.createQuery(" from Product order by calculatedValue desc").list();
    	Assert.assertEquals(new BigDecimal(346.8).setScale(1,RoundingMode.HALF_UP).doubleValue(), results.get(0).getCalculatedValue());
    }
    
}