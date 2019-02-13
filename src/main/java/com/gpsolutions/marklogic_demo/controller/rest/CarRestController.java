package com.gpsolutions.marklogic_demo.controller.rest;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarRestController {
    private final GenericService<CarDTO> carService;

    @Autowired
    public CarRestController(final GenericService<CarDTO> carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public void createCar(final @RequestBody CarDTO newCarDTO) {
        carService.create(newCarDTO);
    }

    @GetMapping("/read")
    public CarDTO getCar(final @RequestParam String id) {
        return carService.read(id);
    }

    @GetMapping("/readAll")
    public List<CarDTO> getAllCars() {
        return carService.readAll();
    }

    @PostMapping("/update")
    public void updateCar(final @RequestBody CarDTO carToUpdate) {
        carService.update(carToUpdate);
    }

    @DeleteMapping("/delete")
    public void deleteCar(final @RequestParam String id){
        carService.delete(id);
    }
}
