package com.vector.persistence.dao;

import com.google.common.base.Preconditions;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class GenericDAO extends AbstractHibernateDAO {

    public void persist(Object entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().persist(entity);
    }

    @SuppressWarnings("unchecked")
    public <T> Collection<T> getByProperty(Class<T> entityClass, Collection<String> names, final String propertyName) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(Restrictions.in(propertyName, names));

        return criteria.list();
    }
}
