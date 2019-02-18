package com.gpsolutions.marklogic_demo.util;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class ClassFieldsResolver<E extends AbstractEntity> {
    private final Class<E> clazz;
    private final Map<String, String> classFields;
    private final CamelCaseToHumanizeConverter converter;

    public ClassFieldsResolver(final Class<E> clazz) {
        this.clazz = clazz;
        classFields = new LinkedHashMap<>();
        this.converter = new CamelCaseToHumanizeConverter();
        resolveFields();
    }

    private void resolveFields() {
        Arrays.asList(clazz.getDeclaredFields()).forEach(field -> {
            final String fieldName = field.getName();
            final String fieldPresentation = converter.convert(fieldName);
            classFields.put(fieldPresentation, fieldName);
        });
    }

    public Map<String, String> getClassFields() {
        return classFields;
    }
}
