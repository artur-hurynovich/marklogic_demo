package com.gpsolutions.marklogic_demo.service;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;

import java.util.List;

public interface GenericService<E extends AbstractEntity> {
    void create(final E e);

    List<E> readAll();
}
