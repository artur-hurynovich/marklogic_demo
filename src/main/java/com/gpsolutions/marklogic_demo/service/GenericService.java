package com.gpsolutions.marklogic_demo.service;

import com.gpsolutions.marklogic_demo.dto.AbstractDTO;

import java.util.List;

public interface GenericService<E extends AbstractDTO> {
    void create(final E e);

    E read(final String id);

    List<E> readAll();

    void update(final E e);

    void delete(final String id);
}
