package com.gpsolutions.marklogic_demo.service.search_service.impl;

import com.gpsolutions.marklogic_demo.service.search_service.GenericSearchCriteria;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;

public class StringSearchCriteria extends GenericSearchCriteria<String> {
    public StringSearchCriteria(final MatchType matchType, final String fieldName, final String searchPattern) {
        super(matchType, fieldName, searchPattern);
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }
}
