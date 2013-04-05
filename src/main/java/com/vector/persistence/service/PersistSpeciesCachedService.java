package com.vector.persistence.service;


import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.vector.entity.dictionary.Dictionary;
import com.vector.entity.dictionary.Species;
import com.vector.persistence.dao.DictionaryDAO;
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
    private GenericService genericService;

    @Resource
    private DictionaryDAO dictionaryDAO;

    @Value("${persistence.batch_size}")
    private int batchSize;

    private Set<Species> entityCache = new HashSet<>();

    public void persist(Species species) {
        if (entityCache.size() < batchSize) {
            entityCache.add(species);
        } else {
            persistBatch(entityCache);
            entityCache.clear();
        }
    }

    private void persistBatch(Collection<Species> speciesCollection) {
        if (speciesCollection.isEmpty()) {
            return;
        }

        genericService.batchPersist(speciesCollection);
        logger.debug("Persisted cache (size:{})", speciesCollection.size());
    }

    private Collection<Species> filterSpecies(final Collection<Species> speciesCollection) {
        Collection<String> speciesNames = Collections2.transform(speciesCollection, SPECIES_NAME_FUNCTION);

        Collection<Species> existentSpecies = dictionaryDAO.getByName(Species.class, speciesNames);
        if (existentSpecies.isEmpty()) {
            return speciesCollection;
        }

        Set<String> existentSpeciesNames = new HashSet<>(Collections2.transform(existentSpecies, SPECIES_NAME_FUNCTION));

        return null;
    }

    public void flush() {
        logger.debug("Flush");
        persistBatch(entityCache);
    }

    private static final Function<Dictionary, String> SPECIES_NAME_FUNCTION = new Function<Dictionary, String>() {
        @Override
        public String apply(Dictionary input) {
            return input.getName();
        }
    };
}
