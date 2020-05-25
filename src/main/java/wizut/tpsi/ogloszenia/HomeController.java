/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia;

import java.sql.SQLException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.services.OffersService;
import wizut.tpsi.ogloszenia.web.OfferFilter;

/**
 *
 * @author Kamil
 */
@Controller
public class HomeController {
    @Autowired
    private OffersService offersService;
    
     @RequestMapping("/home")
    public String home2(Model model) throws SQLException {
        model.addAttribute("cm1",offersService.getCarManufacturer(2));
        model.addAttribute("cm2",offersService.getModel(3));
        return "home";
    }
    
    @GetMapping("/")
    public String home(Model model, OfferFilter offerFilter) {
    List<CarManufacturer> carManufacturers = offersService.getCarManufacturers();
    List<FuelType> fuelTypes = offersService.getFuelTypes();
   // List<CarModel> carModels = offersService.getCarModels();
   
//rozwiazanie z zadania 3
//    List<Offer> offers;
//    List<CarModel> carModels = null;
//   
//if(offerFilter.getManufacturerId()!=null) {
//    offers = offersService.getOffersByManufacturer(offerFilter.getManufacturerId());
//    carModels = offersService.getCarModels(offerFilter.getManufacturerId());
//    if(offerFilter.getModelId()!=null){
//        offers = offersService.getOffersByModel(offerFilter.getModelId());
//    }
//} else {
//    offers = offersService.getOffers();
//    carModels = null;
//}

List<Offer> offers;
List<CarModel> carModels = null;

if(offerFilter.getManufacturerId()!=null){
    carModels = offersService.getCarModels(offerFilter.getManufacturerId());
}
offers = offersService.getOffers(offerFilter);

    model.addAttribute("carManufacturers", carManufacturers);
    model.addAttribute("carModels", carModels);
    model.addAttribute("fuelTypes", fuelTypes);
    model.addAttribute("offers", offers);

    return "offersList";
}
    
    @GetMapping("/offer/{id}")
public String offerDetails(Model model, @PathVariable("id") Integer id) {
    Offer offer = offersService.getOffer(id);
    model.addAttribute("offer", offer);
    return "offerDetails";
}

@GetMapping("/newoffer")
public String newOfferForm(Model model, Offer offer) {
    List<CarModel> carModels = offersService.getCarModels();
List<BodyStyle> bodyStyles = offersService.getBodyStyles();
List<FuelType> fuelTypes = offersService.getFuelTypes();

model.addAttribute("carModels", carModels);
model.addAttribute("bodyStyles", bodyStyles);
model.addAttribute("fuelTypes", fuelTypes);
    return "offerForm";
}

@PostMapping("/newoffer")
public String saveNewOffer(Model model, @Valid Offer offer, BindingResult binding) {
    if(binding.hasErrors()) {
        List<CarModel> carModels = offersService.getCarModels();
        List<BodyStyle> bodyStyles = offersService.getBodyStyles();
        List<FuelType> fuelTypes = offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);

        return "offerForm";
    }
    offer = offersService.createOffer(offer);

    return "redirect:/offer/" + offer.getId();
}
    
}
