package com.gpsolutions.marklogic_demo.entity;

import com.marklogic.client.pojo.annotation.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class AbstractEntity {
    @Getter(AccessLevel.NONE)
    private String id;

    @Id
    public String getId() {
        return id;
    }
}
