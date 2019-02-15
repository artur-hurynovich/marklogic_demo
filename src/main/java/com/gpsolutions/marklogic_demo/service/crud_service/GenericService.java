package com.gpsolutions.marklogic_demo.service.crud_service;

import com.gpsolutions.marklogic_demo.dto.AbstractDTO;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;

import java.util.List;

public interface GenericService<E extends AbstractDTO> {
    void create(final E e);

    E read(final String id);

    List<E> readAll();

    List<E> search(final String searchPattern, final MatchType matchType, final String fieldName);

    List<E> search(final Integer searchPattern, final MatchType matchType, final String fieldName);

    void update(final E e);

    void delete(final String id);
}
