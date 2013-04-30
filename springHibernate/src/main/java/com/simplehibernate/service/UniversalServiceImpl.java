package com.simplehibernate.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simplehibernate.dao.UniversalDoa;

@Service
public class UniversalServiceImpl implements UniversalService {
	
	@Autowired(required=true)
	private UniversalDoa universalDao; 

	public UniversalDoa getUniversalDao() {
		return universalDao;
	}

	public void setUniversalDao(UniversalDoa universalDao) {
		this.universalDao = universalDao;
	}

	@Transactional(readOnly=false)
	public List getAll(Class clazz) {
		return universalDao.getAll(clazz);
	}

	@Transactional(readOnly=true)
	public Object get(Serializable id, Class clazz) {
		return universalDao.get(id,clazz);
	}

	@Transactional(readOnly=false)
	public Object save(Object objectToPersist) {
		universalDao.save(objectToPersist);
		return objectToPersist;
	}

	@Transactional(readOnly=false)
	public void delete(Object object) {
		universalDao.delete(object);
	}

	
}
