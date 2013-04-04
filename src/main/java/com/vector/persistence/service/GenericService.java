package com.vector.persistence.service;

import com.vector.persistence.dao.GenericDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;

@Service
@Transactional
public class GenericService {
    private static final Logger logger = LoggerFactory.getLogger(GenericService.class);

    @Resource
    private GenericDAO genericDAO;

    public void persist(Object entity) {
        genericDAO.persist(entity);
    }

    public void batchPersist(Collection<?> entities) {
        for (Object entity : entities) {
            logger.debug("Persist: {}", entity);
            genericDAO.persist(entity);
        }
    }
}
