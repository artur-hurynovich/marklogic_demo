package com.gpsolutions.marklogic_demo.controller.view;

import com.gpsolutions.marklogic_demo.dto.impl.CarDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarViewController {
    @PostMapping("/create")
    public String createCar(final @ModelAttribute("newCarDto") CarDTO newCarDTO) {
        System.out.println(newCarDTO);
        return "redirect:/carsPage";
    }
}
