package com.gpsolutions.marklogic_demo.entity.impl;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;
import com.gpsolutions.marklogic_demo.enumeration.EngineType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarEntity extends AbstractEntity {
    private String mark;
    private String model;
    private int productionYear;
    private EngineType engineType;
    private double engineCapacity;
}
