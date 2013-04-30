package com.ex.simplebibernate.testhibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.simplehibernate.model.Email;
import com.simplehibernate.model.Heat;
import com.simplehibernate.model.Industry;
import com.simplehibernate.model.Person;
import com.simplehibernate.model.Role;
import com.simplehibernate.model.SpireAccessionNumberMap;
import com.simplehibernate.model.SpireCommonAccessionNumber;
import com.simplehibernate.model.Supplier;
import com.simplehibernate.model.Team;
import com.simplehibernate.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml" })
public class HibernateTest {
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    private Session session;
    private Long fredId;

	private Long userId;

    @Test
    @Transactional
    public final void testHibernate() {
        session=sessionFactory.getCurrentSession();
        User user = new User();
        Role role = new Role();
        user.setEmail("aaf");
        role.setCode("ccf");
        user.getRoles().add(role);
        session.persist(user);
    }
    
    @Test
    @Transactional
    public final void testQuestion2() {
        session=sessionFactory.getCurrentSession();
        Heat heat = new Heat();
        Team team = new Team();
        team.setHeat(heat);
        List<Team>  teams= new ArrayList<Team>();
        teams.add(team);
        team = new Team();
        team.setHeat(heat);
        teams.add(team);
        heat.setTeams(teams);
        session.persist(heat);
    }
    
    @Test
    @Transactional
    public final void testQuery(){
    	session=sessionFactory.getCurrentSession();
    	User user = new User();
        Role role = new Role();
        user.setEmail("aaf");
        role.setCode("ccf");
        user.getRoles().add(role);
        session.persist(user);
    	 Query query = session.createQuery("SELECT obj FROM User obj");
         
         Assert.assertTrue(query.list().size() > 0);
         System.out.println("SIZE+++++++++++++++++"+query.list().size());
    }
    
    @Test
    @Transactional
    public final void testCriteriaQuery2(){
	    session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Supplier.class); 
		criteria.createCriteria("products","p");
		List<Supplier> list = criteria.list();
		
		for (Supplier object : list) {
			object.getClass();
		}
    }
    
    
    
    @Test
    @Transactional
    public final void testCriteriaQuery(){
    	session=sessionFactory.getCurrentSession();
    	Criteria criteria = session.createCriteria(Supplier.class); 
    	criteria.createAlias("products","p");

        ProjectionList projList = Projections.projectionList(); 
        projList.add(Projections.max("p.price"));
        projList.add(Projections.min("p.price")); 
        projList.add(Projections.groupProperty("name"));
        criteria.setProjection(projList);
        List list = criteria.list();
    }
    
    @Before
    @Transactional()
    public final void setupTestuser() {
        session=sessionFactory.getCurrentSession();
        User user = new User();
        Industry ind = new Industry();
        user.setEmail("aaf");
        user.getIndustries().add(ind);
        session.persist(user);
        userId = user.getUserID();
    }
    
    @Test
    @Transactional()
    public final void testHibernate3() {
        session=sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, userId);
        Industry ind = new Industry();
        user.getIndustries().add(ind);
        session.persist(user);
    }

    
    @Transactional()
    public void setupTestPerson(){
    	Person peson = new Person("Johnny");
        session=sessionFactory.getCurrentSession();
//        john = personRepository.save(john);
        session.persist(peson);
    }
    
    
    @Test
    @Transactional
    public final void testFindPersonQuery(){
    	Query query = session.getNamedQuery("findPersonByName").setString("name", "Johnny");
    	Person person = (Person) query.list().get(0);
    	Assert.assertEquals("Johnny", person.getName());
    }
    
    
    @Test
    @Transactional
    public final void testSaveMap(){
    	session=sessionFactory.getCurrentSession();
    	SpireAccessionNumberMap map = new SpireAccessionNumberMap();
    	SpireCommonAccessionNumber number = new SpireCommonAccessionNumber();
    	number.setSpireAccessionNumberMap(map);
    	map.getCommonAccessionNumbers().add(number);
    	session.persist(map);
    	Assert.assertNotNull(map.getId());
    }
    
}