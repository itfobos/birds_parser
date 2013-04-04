package com.vector.persistence.dao;

import com.google.common.base.Preconditions;
import com.vector.entity.dictionary.Dictionary;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class GenericDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void persist(Object entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().persist(entity);
    }

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
