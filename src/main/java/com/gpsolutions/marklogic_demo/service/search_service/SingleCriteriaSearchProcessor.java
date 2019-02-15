package com.gpsolutions.marklogic_demo.service.search_service;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;

import java.io.Serializable;
import java.util.List;

public interface SingleCriteriaSearchProcessor<E extends AbstractEntity, I extends Serializable> {
    void setCriteria(final SearchCriteria searchCriteria);

    List<E> getSearchResult();
}
