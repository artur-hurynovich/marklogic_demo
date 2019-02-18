package com.gpsolutions.marklogic_demo.service.search_service.impl;

import com.gpsolutions.marklogic_demo.service.search_service.GenericSearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;

public class DoubleSearchCriteria extends GenericSearchCriteria<Double> {
    public DoubleSearchCriteria(final MatchType matchType, final String fieldName, final Double searchPattern) {
        super(matchType, fieldName, searchPattern);
    }

    @Override
    public Class<Double> getType() {
        return Double.class;
    }
}
