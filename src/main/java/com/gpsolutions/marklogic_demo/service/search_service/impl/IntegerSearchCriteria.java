package com.gpsolutions.marklogic_demo.service.search_service.impl;

import com.gpsolutions.marklogic_demo.service.search_service.GenericSearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;

public class IntegerSearchCriteria extends GenericSearchCriteria<Integer> {
    public IntegerSearchCriteria(final MatchType matchType, final String fieldName, final Integer searchPattern) {
        super(matchType, fieldName, searchPattern);
    }

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
}
