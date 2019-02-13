package com.gpsolutions.marklogic_demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping({"/", "/cars"})
    public String carsPage() {
        return "cars";
    }

    @RequestMapping("/newCar")
    public String newCarPage() {
        return "new-car";
    }

    @RequestMapping("/editCar")
    public String editCarPage() {
        return "edit-car";
    }
}
