package com.gpsolutions.marklogic_demo.entity.impl;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;
import com.gpsolutions.marklogic_demo.enumeration.EngineType;
import com.marklogic.client.pojo.annotation.PathIndexProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarEntity extends AbstractEntity {
    private String mark;
    private String model;
    @PathIndexProperty(scalarType = PathIndexProperty.ScalarType.INT)
    private int productionYear;
    private EngineType engineType;
    @PathIndexProperty(scalarType = PathIndexProperty.ScalarType.DOUBLE)
    private double engineCapacity;
}
