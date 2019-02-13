package com.gpsolutions.marklogic_demo.converter.impl;

import com.gpsolutions.marklogic_demo.converter.EntityDTOConverter;
import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CarEntityDTOConverter implements EntityDTOConverter<CarEntity, CarDTO> {
    @Override
    public CarEntity convertToEntity(final CarDTO carDTO) {
        final CarEntity carEntity = new CarEntity();
        BeanUtils.copyProperties(carDTO, carEntity);
        return carEntity;
    }

    @Override
    public CarDTO convertToDTO(final CarEntity carEntity) {
        final CarDTO carDTO = new CarDTO();
        BeanUtils.copyProperties(carEntity, carDTO);
        return carDTO;
    }
}
