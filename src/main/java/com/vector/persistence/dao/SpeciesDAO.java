package com.vector.persistence.dao;

import com.vector.entity.dictionary.Species;
import org.springframework.stereotype.Repository;

@Repository
public class SpeciesDAO extends AbstractHibernateDAO<Species> {
    public SpeciesDAO() {
        super(Species.class);
    }
}
