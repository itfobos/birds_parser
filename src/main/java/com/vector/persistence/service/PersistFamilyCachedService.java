package com.vector.persistence.service;


import com.vector.entity.dictionary.Family;
import com.vector.persistence.dao.FamilyDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@Scope("prototype")
public class PersistFamilyCachedService {

    @Resource
    private FamilyDAO familyDAO;

    @Value("${persistence.batch_size}")
    private int batchSize;

    private Set<Family> entityCache = new HashSet<>();

    public void persist(Family family) {
        if (entityCache.size() < batchSize) {
            entityCache.add(family);
        } else {
            persistBatch(entityCache);
        }
    }

    @Transactional
    public void persistBatch(Collection<Family> families) {
        for (Family family : families) {
            familyDAO.save(family);
        }
    }

    public void flush() {
        persistBatch(entityCache);
    }
}
