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
public class CountyQuery {
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
        peson = new Person("Timmy");
        session.persist(peson);
	}
	
	
	@Test
	@Transactional()
	public void testCriteriaQuery(){
		Session session=sessionFactory.getCurrentSession();
		List<String> list = session.createQuery("select p.name FROM Person p where 0 = all elements(p.sent)").list();
		String name = list.get(0).toString();
		Assert.assertEquals("Timmy",name);
	}

}
