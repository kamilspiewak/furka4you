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
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;

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
     public List<BodyStyle> getBodyStyles(){
         String jpql = "select bs from BodyStyle bs order by bs.name";
         TypedQuery<BodyStyle> query = em.createQuery(jpql,BodyStyle.class);
         List<BodyStyle> result = query.getResultList();
         return result;
     }
     
     public List<CarModel> getCarModels(){
         String jpql = "select cm from CarModel cm order by cm.name";
         TypedQuery<CarModel> query = em.createQuery(jpql,CarModel.class);
         List<CarModel> result = query.getResultList();
         return result;
     }
     
     public List<FuelType> getFuelTypes(){
         String jpql = "select ft from FuelType ft order by ft.name";
         TypedQuery<FuelType> query = em.createQuery(jpql,FuelType.class);
         List<FuelType> result = query.getResultList();
         return result;
     }
}
