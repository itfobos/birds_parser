package com.vector.persistence.dao;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenericDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void persist(Object entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().persist(entity);
    }

    public Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
