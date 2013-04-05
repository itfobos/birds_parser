package com.vector.persistence.dao;

import com.vector.entity.dictionary.Species;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SpeciesDAO extends AbstractHibernateDAO {

    public Species getByLatinName(final String latinName) {
        Criteria criteria = getCurrentSession().createCriteria(Species.class);
        criteria.add(Restrictions.eq("nameLatin", latinName));

        //Species name is unique - can get unique result by name.
        return (Species) criteria.uniqueResult();
    }
}
