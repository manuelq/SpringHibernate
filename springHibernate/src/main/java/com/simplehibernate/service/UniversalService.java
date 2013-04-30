package com.simplehibernate.service;

import java.io.Serializable;
import java.util.List;

import com.simplehibernate.dao.UniversalDoa;

public interface UniversalService {
	public abstract List getAll(Class clazz);

	public abstract Object get(Serializable id, Class clazz);

	public abstract Object save(Object objectToPersist);

	public abstract void delete(Object object);

	public abstract void setUniversalDao(UniversalDoa universalDao);

	public abstract UniversalDoa getUniversalDao();
}
