package com.gpsolutions.marklogic_demo.controller.view;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import com.gpsolutions.marklogic_demo.service.crud_service.GenericService;
import com.gpsolutions.marklogic_demo.service.search_service.enumeration.MatchType;
import com.gpsolutions.marklogic_demo.util.EntityClassField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CarViewController {
    private final GenericService<CarDTO> carService;
    private final Set<EntityClassField> entityClassFields;
    @Value("${view.redirect.cars_page}")
    private String redirectCarsPage;

    @Autowired
    public CarViewController(final GenericService<CarDTO> carService, final Set<EntityClassField> entityClassFields) {
        this.carService = carService;
        this.entityClassFields = entityClassFields;
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
        model.addAttribute("entityClassFields", entityClassFields);
        final Class<?> searchPatternClass = entityClassFields.stream().filter(entityClassField ->
                entityClassField.getName().equals(fieldName)).map(EntityClassField::getType).
                collect(Collectors.toList()).iterator().next();
        if (searchPatternClass.isAssignableFrom(Integer.class)) {
            model.addAttribute("cars",
                    carService.search(Integer.valueOf(searchPattern), matchType, fieldName));
        } else if (searchPatternClass.isAssignableFrom(Double.class)){
            model.addAttribute("cars",
                    carService.search(Double.valueOf(searchPattern), matchType, fieldName));
        } else {
            model.addAttribute("cars",
                    carService.search(searchPattern, matchType, fieldName));
        }
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
