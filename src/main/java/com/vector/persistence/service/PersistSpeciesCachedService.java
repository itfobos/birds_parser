package com.vector.persistence.service;


import com.vector.entity.dictionary.Species;
import com.vector.persistence.dao.GenericDAO;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@Scope("prototype")
public class PersistSpeciesCachedService {
    private static final Logger logger = LoggerFactory.getLogger(PersistSpeciesCachedService.class);

    @Resource
    private GenericDAO genericDAO;

    @Value("${persistence.batch_size}")
    private int batchSize;

    private Set<Species> entityCache = new HashSet<>();

    public void persist(Species species) {
        if (entityCache.size() < batchSize) {
            entityCache.add(species);
        } else {
            persistBatch(entityCache);
        }
    }

    private void persistBatch(Collection<Species> speciesCollection) {
        if (speciesCollection.isEmpty()) {
            return;
        }

        Transaction transaction = genericDAO.getCurrentSession().getTransaction();
        transaction.begin();

        try {
            for (Species species : speciesCollection) {
                genericDAO.persist(species);
            }
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }

        transaction.commit();
        logger.debug("Persisted cache (size:{})", speciesCollection.size());
    }

    public void flush() {
        persistBatch(entityCache);
    }
}
