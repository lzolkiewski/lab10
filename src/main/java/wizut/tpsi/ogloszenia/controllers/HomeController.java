package wizut.tpsi.ogloszenia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wizut.tpsi.ogloszenia.services.OffersService;

@Controller
public class HomeController {

    @Autowired
    private OffersService service;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("car_model", service.getCarModel(3));
        model.addAttribute("car_manufacturer", service.getCarManufacturer(2));

        return "/home";
    }

}