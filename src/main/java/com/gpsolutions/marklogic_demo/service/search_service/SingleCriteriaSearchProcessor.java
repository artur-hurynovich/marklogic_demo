package com.gpsolutions.marklogic_demo.service.search_service;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;
import com.gpsolutions.marklogic_demo.service.search_service.impl.DoubleSearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.impl.IntegerSearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.impl.StringSearchCriteria;

import java.util.List;

public interface SingleCriteriaSearchProcessor<E extends AbstractEntity> {
    List<E> getSearchResult(final StringSearchCriteria searchCriteria);

    List<E> getSearchResult(final IntegerSearchCriteria searchCriteria);

    List<E> getSearchResult(final DoubleSearchCriteria searchCriteria);
}
