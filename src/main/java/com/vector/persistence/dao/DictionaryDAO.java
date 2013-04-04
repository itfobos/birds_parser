package com.vector.persistence.dao;

import com.vector.entity.dictionary.Dictionary;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class DictionaryDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }


    public <T extends Dictionary> Collection<T> getByName(Class<T> entityClass, Collection<String> names) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(Restrictions.in("name", names));

        return criteria.list();
    }
}
