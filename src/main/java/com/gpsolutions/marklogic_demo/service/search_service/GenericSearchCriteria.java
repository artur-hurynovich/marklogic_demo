package com.gpsolutions.marklogic_demo.service.search_service;

import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class GenericSearchCriteria<T> {
    private MatchType matchType;
    private String fieldName;
    private T searchPattern;

    public abstract Class<T> getType();
}
