package com.vector.persistence.service;

import com.vector.persistence.dao.GenericDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class GenericService {
    @Resource
    private GenericDAO genericDAO;

    public void persist(Object entity) {
        genericDAO.persist(entity);
    }
}
