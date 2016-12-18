package com.example.hibernate;

import java.util.List;

public interface CP_HibernateDAO {

	public List<?> findAll(Class<?> entityClazz, String... str);

	public void save(Object entity);

}


