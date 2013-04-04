package com.vector.persistence.dao;

import com.google.common.base.Preconditions;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public abstract class AbstractHibernateDAO<T> {

    private final Class<T> clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public AbstractHibernateDAO(final Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    @SuppressWarnings("unchecked")
    public T getById(final Long id) {
        Preconditions.checkArgument(id != null);
        return (T) this.getCurrentSession().get(this.clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return this.getCurrentSession()
                .createQuery("from " + this.clazz.getName()).list();
    }

    public void update(final T entity) {
        Preconditions.checkNotNull(entity);
        this.getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        this.getCurrentSession().delete(entity);
    }

    public void deleteById(final Long entityId) {
        final T entity = this.getById(entityId);
        Preconditions.checkState(entity != null);
        this.delete(entity);
    }

    protected final Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }
}
