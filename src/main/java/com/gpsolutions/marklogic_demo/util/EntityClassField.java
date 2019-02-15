package com.gpsolutions.marklogic_demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntityClassField {
    private String name;
    private String presentation;
    private Class<?> type;
}
