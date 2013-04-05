package com.vector.persistence.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AbstractHibernateDAO {

    @Autowired
    private SessionFactory sessionFactory;

    protected final Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
