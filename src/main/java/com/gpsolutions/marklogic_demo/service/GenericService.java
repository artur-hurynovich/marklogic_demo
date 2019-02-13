package com.gpsolutions.marklogic_demo.service;

import com.gpsolutions.marklogic_demo.dto.AbstractDTO;

import java.util.List;

public interface GenericService<E extends AbstractDTO> {
    void create(final E e);

    List<E> readAll();
}
