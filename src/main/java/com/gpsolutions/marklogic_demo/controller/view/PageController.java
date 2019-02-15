package com.gpsolutions.marklogic_demo.controller.view;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.enumeration.EngineType;
import com.gpsolutions.marklogic_demo.service.crud_service.GenericService;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/newCarPage")
    public String newCarPage(final Model model) {
        model.addAttribute("newCar", new CarDTO());
        model.addAttribute("engineTypesMap", EngineType.toEngineTypesMap());
        model.addAttribute("years", years);
        return "new-car";
    }

    @GetMapping({"/", "/carsPage"})
    public String carsPage(final Model model) {
        model.addAttribute("cars", carService.readAll());
        model.addAttribute("matchTypes", MatchType.toMatchTypesMap());
        return "cars";
    }

    @PostMapping("/editCarPage")
    public String editCarPage(final @RequestParam String id, final Model model) {
        final CarDTO carToEdit = carService.read(id);
        model.addAttribute("carToEdit", carToEdit);
        model.addAttribute("engineTypesMap", EngineType.toEngineTypesMap());
        model.addAttribute("years", years);
        return "edit-car";
    }
}
