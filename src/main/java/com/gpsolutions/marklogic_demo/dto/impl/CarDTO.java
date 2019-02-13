package com.gpsolutions.marklogic_demo.dto.impl;

import com.gpsolutions.marklogic_demo.dto.AbstractDTO;
import com.gpsolutions.marklogic_demo.enumeration.EngineType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarDTO extends AbstractDTO {
    private String mark;
    private String model;
    private int productionYear;
    private EngineType engineType;
    private double engineCapacity;
}
