/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.services;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
     public List<CarManufacturer> getCarManufacturers() {
    String jpql = "select cm from CarManufacturer cm order by cm.name";
    TypedQuery<CarManufacturer> query = em.createQuery(jpql, CarManufacturer.class);
    List<CarManufacturer> result = query.getResultList();
    return result;
}
}
