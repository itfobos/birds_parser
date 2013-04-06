package com.vector.persistence.service;


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
public class CachingService {
    private static final Logger logger = LoggerFactory.getLogger(CachingService.class);

    @Resource
    private GenericService genericService;

    @Value("${persistence.batch_size}")
    private int batchSize;

    private Set<Object> entityCache = new HashSet<>();

    public void persist(Object entity) {
        if (entityCache.size() < batchSize) {
            entityCache.add(entity);
        } else {
            persistBatch(entityCache);
            entityCache.clear();
        }
    }

    private void persistBatch(Collection<Object> speciesCollection) {
        if (speciesCollection.isEmpty()) {
            return;
        }

        genericService.batchPersist(speciesCollection);
        logger.debug("Persisted cache (size:{})", speciesCollection.size());
    }

    public void flush() {
        logger.debug("Flush");
        persistBatch(entityCache);
    }
}
