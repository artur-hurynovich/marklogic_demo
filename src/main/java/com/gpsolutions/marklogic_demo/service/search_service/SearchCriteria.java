package com.gpsolutions.marklogic_demo.service.search_service;

import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchCriteria {
    private Object searchPattern;
    private MatchType matchType;
    private String fieldName;
}
