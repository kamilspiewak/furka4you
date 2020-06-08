/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.web.OfferFilter;

/**
 *
 * @author Kamil
 */
@Service
@Transactional
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
     
     public List<CarModel> getCarModels(int manufacturerId) {
    String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";

    TypedQuery<CarModel> query = em.createQuery(jpql, CarModel.class);
    query.setParameter("id", manufacturerId);

    return query.getResultList();
}
 
     
     public List<FuelType> getFuelTypes(){
         String jpql = "select ft from FuelType ft order by ft.name";
         TypedQuery<FuelType> query = em.createQuery(jpql,FuelType.class);
         List<FuelType> result = query.getResultList();
         return result;
     }
     
     public List<Offer> getOffers(){
         String jpql = "select off from Offer off order by off.id";
         TypedQuery<Offer> query = em.createQuery(jpql,Offer.class);
         List<Offer> result = query.getResultList();
         return result;
     }
     public Offer getOffer(int id) {
        return em.find(Offer.class, id);
    }
     
     public List<Offer> getOffersByModel(int modelId) {
    String jpql = "select off from Offer off where off.model.id = :id order by off.title";

    TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
    query.setParameter("id", modelId);


    return query.getResultList();
}
     
     public List<Offer> getOffersByManufacturer(int manufacturerId) {
    String jpql = "select off from Offer off where off.model.manufacturer.id = :id order by off.title";

    TypedQuery<Offer> query = em.createQuery(jpql, Offer.class);
    query.setParameter("id", manufacturerId);

    return query.getResultList();
}
     public Offer createOffer(Offer offer) {
    em.persist(offer);
    return offer;
}

     
     public List<Offer> getOffers(OfferFilter filter){
//         if(pageNumber==null){
//             pageNumber = 1;
//         }
//         if(pageNumber<1){
//             pageNumber=1;
//         }
         
         String jpql = "select off from Offer off where 1=1 ";
         if(filter.getModelId()!=null && filter.getModelId()!=-1){
             jpql = jpql.concat("and off.model.id = :id ");
         }
         if(filter.getManufacturerId()!=null && filter.getManufacturerId()!=-1){
             jpql = jpql.concat("and off.model.manufacturer.id = :id2 ");
         }
         if(filter.getDateFrom()!=null){
             jpql = jpql.concat("and off.year >= :yr1 ");
         }
         if(filter.getDateTo()!=null){
             jpql = jpql.concat("and off.year <= :yr2 ");
         }
         if(filter.getFuelTypeId()!=null && filter.getFuelTypeId()!=-1){
             jpql = jpql.concat("and off.fuelType.id = :ft ");
         }
         if(filter.getOfferDes()!=null){
             jpql = jpql.concat("and off.description LIKE :pattern ");
         }
         
         if(filter.getSort()==null || filter.getSort()==-1) jpql = jpql.concat("order by off.title");
         else if(filter.getSort()==0) jpql = jpql.concat("order by off.year");
         else if(filter.getSort()==1) jpql = jpql.concat("order by off.year desc");
         else if(filter.getSort()==2) jpql = jpql.concat("order by off.price");
         else if(filter.getSort()==3) jpql = jpql.concat("order by off.price desc");
         else if(filter.getSort()==4) jpql = jpql.concat("order by off.data");
         else if(filter.getSort()==5) jpql = jpql.concat("order by off.data desc");
         TypedQuery<Offer> query = em.createQuery(jpql,Offer.class);
         if(filter.getModelId()!=null && filter.getModelId()!=-1){
             query.setParameter("id", filter.getModelId());
         }
         if(filter.getManufacturerId()!=null && filter.getManufacturerId()!=-1){
             query.setParameter("id2", filter.getManufacturerId());
         }
         if(filter.getDateFrom()!=null){
             query.setParameter("yr1", filter.getDateFrom());
         }
         if(filter.getDateTo()!=null){
            query.setParameter("yr2", filter.getDateTo());
         }
         if(filter.getFuelTypeId()!=null && filter.getFuelTypeId()!=-1){
             query.setParameter("ft", filter.getFuelTypeId());
         }
         if(filter.getOfferDes()!=null){
             
             query.setParameter("pattern", "%"+filter.getOfferDes()+"%");
         }
       //  int pageNumber = 1;
//         int pageSize = 2;
//         query.setFirstResult((pageNumber-1)*pageSize);
//         query.setMaxResults(pageSize);
//         
//         int temp = result.size()/pageSize;
//         if((result.size()%pageSize)!=0){
//             temp++;
//         }
//         if(pageNumber>temp){
//             pageNumber=temp;
//         }
//         
List<Offer> result = query.getResultList();
         return result;
     }
     
     public Offer deleteOffer(Integer id) {
    Offer offer = em.find(Offer.class, id);
    em.remove(offer);
    return offer;
}
     public Offer saveOffer(Offer offer) {
    return em.merge(offer);
}
    
}