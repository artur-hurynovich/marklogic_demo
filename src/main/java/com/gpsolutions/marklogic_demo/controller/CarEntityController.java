package com.gpsolutions.marklogic_demo.controller;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
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
    private final GenericService<CarDTO> carDTOService;

    @Autowired
    public CarEntityController(final GenericService<CarDTO> carDTOService) {
        this.carDTOService = carDTOService;
    }

    @GetMapping("/readAll")
    public List<CarDTO> getAllCarsEntities() {
        return carDTOService.readAll();
    }

    @PostMapping("/create")
    public void createCarEntity(final @RequestBody CarDTO newCarDTO) {
        carDTOService.create(newCarDTO);
    }
}
