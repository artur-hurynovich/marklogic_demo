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
public class MarkLogicCarService implements GenericService<CarDTO> {
    private final GenericRepository<CarEntity> carRepository;
    private final EntityDTOConverter<CarEntity, CarDTO> carConverter;

    @Autowired
    public MarkLogicCarService(final GenericRepository<CarEntity> carRepository,
                               final EntityDTOConverter<CarEntity, CarDTO> carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

    @Override
    public void create(final CarDTO carDTO) {
        carRepository.create(carConverter.convertToEntity(carDTO));
    }

    @Override
    public CarDTO read(final String id) {
        return carConverter.convertToDTO(carRepository.read(id));
    }

    @Override
    public List<CarDTO> readAll() {
        return carRepository.readAll().stream().map(carConverter::convertToDTO).collect(Collectors.toList());
    }
}
