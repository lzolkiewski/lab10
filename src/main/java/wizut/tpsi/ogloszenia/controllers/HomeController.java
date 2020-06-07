package wizut.tpsi.ogloszenia.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.services.OffersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private OffersService service;

    /**
     * 
     * @param model
     * @return the home.html (Furka4U part 1)
     */
    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("car_model", service.getCarModel(3));
        model.addAttribute("car_manufacturer", service.getCarManufacturer(2));

        return "home";
    }

    /**
     * 
     * @param model
     * @return the offersList.html (Furka4U part 2)
     */
    @RequestMapping("/")
    public String offersList(Model model) {
        List<CarManufacturer> carManufacturers = service.getCarManufacturers();
        List<CarModel> carModels = service.getCarModels();
        List<Offer> offers = service.getOffers();

        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("carModels", carModels);
        model.addAttribute("offers", offers);

        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = service.getOffer(id);
        model.addAttribute("offer", offer);

        return "offerDetails";
    }

}