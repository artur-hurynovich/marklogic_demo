package com.gpsolutions.marklogic_demo.controller;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {
    private final GenericService<CarDTO> carDTOService;

    @Autowired
    public CarController(final GenericService<CarDTO> carDTOService) {
        this.carDTOService = carDTOService;
    }

    @PostMapping("/create")
    public void createCar(final @RequestBody CarDTO newCarDTO) {
        carDTOService.create(newCarDTO);
    }

    @GetMapping
    public CarDTO getCar(final @RequestParam String id) {
        return carDTOService.read(id);
    }

    @GetMapping("/readAll")
    public List<CarDTO> getAllCars() {
        return carDTOService.readAll();
    }
}
