package com.vector.persistence.service;

import com.vector.entity.dictionary.Species;
import com.vector.persistence.dao.SpeciesDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SpeciesService {

    @Resource
    private SpeciesDAO speciesDAO;

    @Transactional(readOnly = true)
    public Species getByLatinName(final String latinName) {
        return speciesDAO.getByLatinName(latinName);
    }

}
