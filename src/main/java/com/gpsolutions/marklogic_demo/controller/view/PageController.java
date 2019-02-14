package com.gpsolutions.marklogic_demo.controller.view;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.enumeration.EngineType;
import com.gpsolutions.marklogic_demo.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {
    private final GenericService<CarDTO> carService;
    private final List<Integer> years;

    @Autowired
    public PageController(final GenericService<CarDTO> carService, final List<Integer> years) {
        this.carService = carService;
        this.years = years;
    }

    @GetMapping({"/", "/carsPage"})
    public String carsPage(final Model model) {
        model.addAttribute("cars", carService.readAll());
        return "cars";
    }

    @GetMapping("/newCarPage")
    public String newCarPage(final Model model) {
        model.addAttribute("newCarDto", new CarDTO());
        model.addAttribute("engineTypesMap", EngineType.toEngineTypesMap());
        model.addAttribute("years", years);
        return "new-car";
    }

    @GetMapping("/editCarPage")
    public String editCarPage() {
        return "edit-car";
    }
}
