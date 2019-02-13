package com.gpsolutions.marklogic_demo.entity;

import com.marklogic.client.pojo.annotation.Id;
import lombok.Data;

@Data
public class AbstractEntity {
    @Id
    private String id;
}
