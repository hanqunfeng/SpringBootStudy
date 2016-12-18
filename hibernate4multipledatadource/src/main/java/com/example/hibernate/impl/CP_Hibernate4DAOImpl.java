package com.example.hibernate.impl;

import com.example.hibernate.CP_HibernateDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public class CP_Hibernate4DAOImpl implements CP_HibernateDAO {


    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    private Session getHibernateSession() {
        Session session = sessionFactory.openSession();
        return session;
    }


    /*
     * @see com.example.hibernate.CP_HibernateDAO#findAll()
     */
    @Override
    public List<?> findAll(Class<?> entityClazz, String... str) {
        DetachedCriteria dc = DetachedCriteria.forClass(entityClazz);
        List<?> list = findAllByCriteria(dc);
        return list;
    }


    /*
     * @see com.example.hibernate.CP_HibernateDAO#save(java.lang.Object)
     */
    @Override
    public void save(Object entity) {

        getHibernateSession().save(entity);
        getHibernateSession().flush();
    }


    public List<?> findAllByCriteria(DetachedCriteria detachedCriteria) {
        // TODO Auto-generated method stub
        Criteria criteria = detachedCriteria
                .getExecutableCriteria(getHibernateSession());
        return criteria.list();
    }

}


