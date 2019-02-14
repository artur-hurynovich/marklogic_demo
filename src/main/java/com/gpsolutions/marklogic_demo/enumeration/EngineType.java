package com.gpsolutions.marklogic_demo.enumeration;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public enum EngineType {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    HYBRID("Hybrid"),
    ELECTRIC("Electric");

    private final String key;

    EngineType(final String key) {
        this.key = key;
    }

    public static Map<String, String> toEngineTypesMap() {
        final Map<String, String> engineTypesMap = new LinkedHashMap<>();
        Arrays.asList(EngineType.values()).forEach(engineType -> engineTypesMap.put(engineType.key, engineType.name()));
        return engineTypesMap;
    }
}
