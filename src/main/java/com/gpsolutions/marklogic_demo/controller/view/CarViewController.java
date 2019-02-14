package com.gpsolutions.marklogic_demo.controller.view;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarViewController {
    private final GenericService<CarDTO> carService;
    @Value("${view.redirect.cars_page}")
    private String redirectCarsPage;

    @Autowired
    public CarViewController(final GenericService<CarDTO> carService) {
        this.carService = carService;
    }

    @PostMapping("/create")
    public String createCar(final @ModelAttribute CarDTO newCar) {
        carService.create(newCar);
        return redirectCarsPage;
    }

    @PostMapping("/update")
    public String updateCar(final @ModelAttribute CarDTO carToUpdate) {
        carService.update(carToUpdate);
        return redirectCarsPage;
    }

    @PostMapping("/delete")
    public String deleteCar(final @RequestParam String id) {
        carService.delete(id);
        return redirectCarsPage;
    }
}
