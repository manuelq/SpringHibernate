package com.simplehibernate.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class UniversalDoa {
	@Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
	
	public List getAll(@SuppressWarnings("rawtypes") Class clazz) {
        return getSessionFactory().getCurrentSession().createQuery("SELECT obj FROM "+clazz.getName()+" obj").list();
    }

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
 
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Object get(Serializable id, Class clazz) {
		 return sessionFactory.getCurrentSession().get(clazz, id);
	}

	public Object save(Object objectToPersist) {
		sessionFactory.getCurrentSession().persist(objectToPersist);
		return objectToPersist;
	}
	
	
	public void delete(Object object) {
		sessionFactory.getCurrentSession().delete(object);
	}
	
}
