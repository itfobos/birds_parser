package com.vector.persistence.dao;

import com.vector.entity.dictionary.Family;
import org.springframework.stereotype.Repository;

@Repository
public class FamilyDAO extends AbstractHibernateDAO<Family> {
    public FamilyDAO() {
        super(Family.class);
    }
}
