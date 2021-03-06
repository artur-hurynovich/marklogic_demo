package com.gpsolutions.marklogic_demo.controller.view;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.entity.impl.CarEntity;
import com.gpsolutions.marklogic_demo.service.crud_service.GenericService;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import com.gpsolutions.marklogic_demo.util.ClassFieldsResolver;
import com.gpsolutions.marklogic_demo.util.SearchPatternClassQualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarViewController {
    private final GenericService<CarDTO> carService;
    private final ClassFieldsResolver<CarEntity> classFieldsResolver;
    private final SearchPatternClassQualifier searchPatternClassQualifier;
    @Value("${view.redirect.cars_page}")
    private String redirectCarsPage;

    @Autowired
    public CarViewController(final GenericService<CarDTO> carService, final ClassFieldsResolver<CarEntity> classFieldsResolver,
                             final SearchPatternClassQualifier searchPatternClassQualifier) {
        this.carService = carService;
        this.classFieldsResolver = classFieldsResolver;
        this.searchPatternClassQualifier = searchPatternClassQualifier;
    }

    @PostMapping("/create")
    public String createCar(final @ModelAttribute CarDTO newCar) {
        carService.create(newCar);
        return redirectCarsPage;
    }

    @PostMapping("/search")
    public String searchCar(final @RequestParam String searchPattern,
                            final @RequestParam MatchType matchType,
                            final @RequestParam(required = false) String fieldName,
                            final Model model) {
        model.addAttribute("classFields", classFieldsResolver.getClassFields());
        final Class<?> searchPatternClass = searchPatternClassQualifier.qualifyClass(searchPattern);
        model.addAttribute("cars",
                carService.search(searchPattern, searchPatternClass,  matchType, fieldName));
        model.addAttribute("matchTypes", MatchType.toMatchTypesMap());
        return "cars";
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
