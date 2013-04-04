package com.vector.persistence.service;

import com.google.common.collect.Iterables;
import com.vector.entity.dictionary.Dictionary;
import com.vector.persistence.dao.DictionaryDAO;
import com.vector.persistence.dao.GenericDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;

@Service
@Transactional
public class DictionaryService {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    @Resource
    private GenericDAO genericDAO;

    @Resource
    private DictionaryDAO dictionaryDAO;

    @SuppressWarnings("unchecked")
    public <T extends Dictionary> T persistWithNameCheck(T entity) {
        Collection<T> dictionaries = (Collection<T>) dictionaryDAO.getByName(entity.getClass(), Collections.singleton(entity.getName()));

        T result;
        if (dictionaries.isEmpty()) {
            genericDAO.persist(entity);
            result = entity;
        } else {
            result = Iterables.getFirst(dictionaries, null);
            logger.warn("Entity is already exist: {}", result);
        }

        return result;
    }
}
