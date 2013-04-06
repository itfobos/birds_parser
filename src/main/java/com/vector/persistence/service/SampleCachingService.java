package com.vector.persistence.service;

import com.vector.entity.Sample;
import com.vector.entity.dictionary.Species;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Scope("prototype")
public class SampleCachingService {
    private static final Logger logger = LoggerFactory.getLogger(SampleCachingService.class);

    @Resource
    private SpeciesService speciesService;

    @Resource
    private CachingService cachingService;

    private Species currentSpecies;

    /**
     * Set species and persist
     */
    public void persist(Sample sample) {
        sample.setSpecies(getSpecies(sample.getSpeciesLatinName()));

        cachingService.persist(sample);
    }

    private Species getSpecies(final String speciesLatinName) {
        if (currentSpecies != null && currentSpecies.getNameLatin().equals(speciesLatinName)) {
            return currentSpecies;
        } else {
            currentSpecies = speciesService.getByLatinName(speciesLatinName);
            return currentSpecies;
        }
    }
}
