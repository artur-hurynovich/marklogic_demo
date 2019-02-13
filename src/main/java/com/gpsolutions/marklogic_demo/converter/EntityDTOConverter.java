package com.gpsolutions.marklogic_demo.converter;

import com.gpsolutions.marklogic_demo.dto.AbstractDTO;
import com.gpsolutions.marklogic_demo.entity.AbstractEntity;

public interface EntityDTOConverter<E extends AbstractEntity, D extends AbstractDTO> {
    E convertToEntity(final D d);

    D convertToDTO(final E e);
}
