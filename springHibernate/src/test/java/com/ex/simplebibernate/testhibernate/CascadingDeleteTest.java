package com.ex.simplebibernate.testhibernate;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.simplehibernate.model.Child;
import com.simplehibernate.model.Parent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
@TransactionConfiguration(defaultRollback = false)
public class CascadingDeleteTest {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	private Long parentId;

	@Before
	public void setUpTestData() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Parent parent = new Parent();
		Set<Child> children = new HashSet<Child>();
		children.add(new Child());
		parent.setChildren(children);
		session.persist(parent.getChildren().iterator().next());
		session.persist(parent);
		parentId = parent.getId();
		tx.commit();
		session.close();
	}
	
	
	@Test
	public void testDelete() {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Parent parent = (Parent) session.get(Parent.class, parentId);
		session.delete(parent);
		tx.commit();
		session.close();
	}
	
}
