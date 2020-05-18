/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.services.OffersService;

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
    public String home(Model model) {
    return "offersList";
}
    
}
