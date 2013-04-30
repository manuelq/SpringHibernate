package com.ex.simplebibernate.testhibernate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.simplehibernate.model.Email;
import com.simplehibernate.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class DistinctCriteriaQuery {
	 @Autowired
	    @Qualifier("sessionFactory")
	    private SessionFactory sessionFactory;
	
	@Before
	public void setup() {
		Person peson = new Person("Johnny");
        Session session=sessionFactory.getCurrentSession();
        Email email = new Email();
        email.setSubject("test");
        email.setSender(peson);
        peson.setSent(new ArrayList<Email>());
        peson.getSent().add(email);
        email = new Email();
        email.setSubject("test");
        email.setSender(peson);
        peson.getSent().add(email);
        email = new Email();
        email.setSubject("test2");
        peson.getSent().add(email);
        email.setSender(peson);
        email = new Email();
        email.setSubject("test2");
        email.setSender(peson);
        peson.getSent().add(email);
        session.persist(peson);
	}
	
	
	@Test
	 @Transactional()
	public void testCriteriaQuery(){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Person.class,"p"); 
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createCriteria("sent","s").add(Restrictions.eq("s.subject","test"));
		List<Person> list = criteria.list();
		Person person = list.get(0);
		Assert.assertEquals(1, list.size());
	}

	@Test
	@Transactional()
	public void testCriteriaQuery2(){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Person.class); 
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		criteria.createAlias("sent","s").add(Restrictions.eq("s.subject","test"));
		List<Person> list = criteria.list();
		Assert.assertEquals(1, list.size());
	}
}
