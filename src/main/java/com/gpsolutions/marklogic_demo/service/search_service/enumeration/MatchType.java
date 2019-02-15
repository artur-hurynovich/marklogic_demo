package com.gpsolutions.marklogic_demo.service.search_service.enumeration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum MatchType {
    FULL_MATCH("Full match"),
    PARTIAL_MATCH("Partial match"),
    LESS_THAN("Less than"),
    GREATER_THAN("Greater than");

    private final String key;

    MatchType(final String key) {
        this.key = key;
    }

    public static Map<String, String> toMatchTypesMap() {
        final Map<String, String> matchTypesMap = new LinkedHashMap<>();
        Arrays.asList(MatchType.values()).forEach(matchType -> matchTypesMap.put(matchType.key, matchType.name()));
        return matchTypesMap;
    }
}
