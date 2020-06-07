package wizut.tpsi.ogloszenia.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.services.OffersService;
import wizut.tpsi.ogloszenia.web.OfferFilter;
// import wizut.tpsi.ogloszenia.web.OfferRepository;
import wizut.tpsi.ogloszenia.web.OfferSorter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private OffersService service;

    // @Autowired
    // private OfferRepository offerRepo;

    /**
     *
     * @param model
     * @return the home.html (Furka4U part 1)
     */
    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("car_model", service.getCarModel(3));
        model.addAttribute("car_manufacturer", service.getCarManufacturer(2));
        model.addAttribute("cmdls", service.getOffersByManufacturer(2));
        return "home";
    }

    /**
     *
     * @param model
     * @return the offersList.html (Furka4U part 2, 3, 4)
     */
    @RequestMapping("/")
    public String offersList(Model model, OfferFilter offerFilter, OfferSorter offerSorter,
            @RequestParam(defaultValue = "0") int page) {
        List<Offer> offers;
        List<CarManufacturer> carManufacturers = service.getCarManufacturers();
        List<FuelType> fuelTypes = service.getFuelTypes();

        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("fuelTypes", fuelTypes);

        if (offerFilter.getManufacturerId() != null || offerFilter.getModelId() != null
                || offerFilter.getYearFrom() != null || offerFilter.getYearTo() != null
                || offerFilter.getFuelTypeId() != null) {
            if (offerFilter.getManufacturerId() != null) {
                model.addAttribute("carModels", service.getCarModels(offerFilter.getManufacturerId()));
            }
            offers = service.getOffers(offerFilter, offerSorter);
        } else if (offerFilter.getDescription() != null) {
            offers = service.getOffersByDescription(offerFilter.getDescription());
        } else {
            offers = service.getOffers();

            if (offerSorter.getPrice() != null || offerSorter.getYear() != null || offerSorter.getAddDate() != null) {
                offers = service.getOffersInOrder(offerSorter);
            }
        }

        model.addAttribute("offers", offers);

        // limit the offers shown and pagination
        // model.addAttribute("data", offerRepo.findAll(PageRequest.of(page,
        // 20)));//limit
        // model.addAttribute("currentPage", page);

        return "offersList";
    }

    // post methods
    @PostMapping("/newoffer")
    public String saveNewOffer(Model model, @Valid Offer offer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<CarModel> carModels = service.getCarModels();
            List<BodyStyle> bodyStyles = service.getBodyStyles();
            List<FuelType> fuelTypes = service.getFuelTypes();

            model.addAttribute("header", "Nowe ogłoszenie");
            model.addAttribute("action", "/newoffer");
            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "offerForm";
        }
        offer.setAddDate(new Date());
        offer = service.createOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer,
            BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/editoffer/" + id);

            List<CarModel> carModels = service.getCarModels();
            List<BodyStyle> bodyStyles = service.getBodyStyles();
            List<FuelType> fuelTypes = service.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "offerForm";
        }

        service.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }

    // get methods
    @GetMapping("/{id}")
    public String manufaturerFilter() {
        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = service.getOffer(id);
        model.addAttribute("offer", offer);

        return "offerDetails";
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Model model, Offer offer) {
        List<CarModel> carModels = service.getCarModels();
        List<BodyStyle> bodyStyles = service.getBodyStyles();
        List<FuelType> fuelTypes = service.getFuelTypes();

        model.addAttribute("header", "Nowe ogłoszenie");
        model.addAttribute("action", "/newoffer");
        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);

        return "offerForm";
    }

    @RequestMapping("/deleteoffer/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = service.deletOffer(id);

        model.addAttribute("offer", offer);
        return "deleteOffer";
    }

    @GetMapping("/editoffer/{id}")
    public String editOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = service.getOffer(id);
        List<CarModel> carModels = service.getCarModels();
        List<BodyStyle> bodyStyles = service.getBodyStyles();
        List<FuelType> fuelTypes = service.getFuelTypes();

        model.addAttribute("header", "Edycja ogłoszenia");
        model.addAttribute("action", "/editoffer/" + id);

        model.addAttribute("offer", offer);

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);

        return "offerForm";
    }

}