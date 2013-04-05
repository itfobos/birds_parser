package com.vector.persistence.service;

import com.vector.entity.Sample;
import com.vector.entity.dictionary.Species;
import com.vector.persistence.dao.SpeciesDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope("prototype")
public class SampleCachedService {
    private static final Logger logger = LoggerFactory.getLogger(SampleCachedService.class);

    @Resource
    private SpeciesDAO speciesDAO;

    @Resource
    private GenericService genericService;

    private Species currentSpecies;

    /**
     * Set species and persist
     */
    public void persist(Sample sample) {
        sample.setSpecies(getSpecies(sample.getSpeciesLatinName()));
        //TODO: persist with caching. Maybe make generic cached service.
    }

    public Species getSpecies(final String speciesLatinName) {
        if (currentSpecies != null && currentSpecies.getNameLatin().equals(speciesLatinName)) {
            return currentSpecies;
        } else {
            currentSpecies = speciesDAO.getByLatinName(speciesLatinName);
            return currentSpecies;
        }
    }
}
