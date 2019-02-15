package com.gpsolutions.marklogic_demo.service.search_service;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;
import com.marklogic.client.pojo.PojoRepository;

import java.io.Serializable;

public abstract class AbstractSingleCriteriaSearchProcessor<E extends AbstractEntity, I extends Serializable> implements SingleCriteriaSearchProcessor<E, I> {
    private final PojoRepository<E, I> pojoRepository;
    private SearchCriteria searchCriteria;

    protected AbstractSingleCriteriaSearchProcessor(final PojoRepository<E, I> pojoRepository) {
        this.pojoRepository = pojoRepository;
    }

    @Override
    public void setCriteria(final SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    protected PojoRepository<E, I> getPojoRepository() {
        return pojoRepository;
    }

    protected SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }
}
