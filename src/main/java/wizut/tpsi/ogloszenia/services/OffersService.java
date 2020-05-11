/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;

/**
 *
 * @author Kamil
 */
@Service
public class OffersService {
     @PersistenceContext
    private EntityManager em;
     
     public CarManufacturer getCarManufacturer(int id) {
        return em.find(CarManufacturer.class, id);
    }
     
     public CarModel getModel(int id){
         return em.find(CarModel.class,id);
     }
     
}
