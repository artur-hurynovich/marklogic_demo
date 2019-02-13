package com.gpsolutions.marklogic_demo.controller;

import com.gpsolutions.marklogic_demo.entity.car.CarEntity;
import com.gpsolutions.marklogic_demo.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarEntityController {
    private final GenericService<CarEntity> carEntityService;

    @Autowired
    public CarEntityController(final GenericService<CarEntity> carEntityService) {
        this.carEntityService = carEntityService;
    }

    @GetMapping("/readAll")
    public List<CarEntity> getAllCarsEntities() {
        return carEntityService.readAll();
    }

    @PostMapping("/create")
    public void createCarEntity(final @RequestBody CarEntity newCarEntity) {
        carEntityService.create(newCarEntity);
    }
}
