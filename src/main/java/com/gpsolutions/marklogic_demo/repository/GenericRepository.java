package com.gpsolutions.marklogic_demo.repository;

import com.gpsolutions.marklogic_demo.entity.AbstractEntity;

import java.util.List;

public interface GenericRepository<E extends AbstractEntity> {
    void create(final E e);

    List<E> readAll();
}