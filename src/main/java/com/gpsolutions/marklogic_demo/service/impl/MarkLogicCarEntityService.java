package com.gpsolutions.marklogic_demo.service.impl;

import com.gpsolutions.marklogic_demo.converter.EntityDTOConverter;
import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import com.gpsolutions.marklogic_demo.repository.GenericRepository;
import com.gpsolutions.marklogic_demo.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarkLogicCarEntityService implements GenericService<CarDTO> {
    private final GenericRepository<CarEntity> carEntityRepository;
    private final EntityDTOConverter<CarEntity, CarDTO> carConverter;

    @Autowired
    public MarkLogicCarEntityService(final GenericRepository<CarEntity> carEntityRepository,
                                     final EntityDTOConverter<CarEntity, CarDTO> carConverter) {
        this.carEntityRepository = carEntityRepository;
        this.carConverter = carConverter;
    }

    @Override
    public void create(final CarDTO carDTO) {
        carEntityRepository.create(carConverter.convertToEntity(carDTO));
    }

    @Override
    public List<CarDTO> readAll() {
        return carEntityRepository.readAll().stream().map(carConverter::convertToDTO).collect(Collectors.toList());
    }
}
