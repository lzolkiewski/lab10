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
import wizut.tpsi.ogloszenia.jpa.Offer;

@Service
public class OffersService {
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 
     * @param id manufacturer id
     * @return manufacturer by id
     */
    public CarManufacturer getCarManufacturer(int id) {
        return entityManager.find(CarManufacturer.class, id);
    }

    /**
     * 
     * @param id model id
     * @return car model by id
     */
    public CarModel getCarModel(int id) {
        return entityManager.find(CarModel.class, id);
    }

    /**
     * 
     * @param id offer id
     * @return offer by id
     */
    public Offer getOffer(int id) {
        return entityManager.find(Offer.class, id);
    }

    /**
     * 
     * @param manufacturerId id of specyfic manufacturer
     * @return list of car models by manufacturer
     */
    public List<CarModel> getCarModels(int manufacturerId) {
        String jpql = "select cm from CarModel cm where cm.manufacturer.id = :id order by cm.name";
        TypedQuery<CarModel> query = entityManager.createQuery(jpql, CarModel.class);
        query.setParameter("id", manufacturerId);
        return query.getResultList();
    }

    /**
     * 
     * @param modelId id of specyfic model
     * @return list of offers by model
     */
    public List<Offer> getOffersByModel(int modelId) {
        String jpql = "select offer from Offer offer where offer.model.id = :id order by offer.title";
        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        query.setParameter("id", modelId);
        return query.getResultList();
    }

    /**
     * 
     * @param manufacturerId id of specyfic manufacturer
     * @return list of offers by manufacturer
     */
    public List<Offer> getOffersByManufacturer(int manufacturerId) {
        String jpql = "select offer from Offer offer where offer.manufacturer.id = :id order by offer.title";
        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);
        return query.getResultList();
    }

    /**
     * 
     * @return offers list
     */
    public List<Offer> getOffers() {
        String jpql = "select offer from Offer offer order by offer.title";
        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        List<Offer> result = query.getResultList();
        return result;
    }

    /**
     * 
     * @return list of manufacturers
     */
    public List<CarManufacturer> getCarManufacturers() {
        String jpql = "select cm from CarManufacturer cm order by cm.name";
        TypedQuery<CarManufacturer> query = entityManager.createQuery(jpql, CarManufacturer.class);
        List<CarManufacturer> result = query.getResultList();
        return result;
    }

    /**
     * 
     * @return list of car models
     */
    public List<CarModel> getCarModels() {
        String jpql = "select cm from CarModel cm order by cm.name";
        TypedQuery<CarModel> query = entityManager.createQuery(jpql, CarModel.class);
        List<CarModel> result = query.getResultList();
        return result;
    }

    /**
     * 
     * @return list of body styles
     */
    public List<BodyStyle> getBodyStyles() {
        String jpql = "select bs form BodyStyle bs order by bs.name";
        TypedQuery<BodyStyle> query = entityManager.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;
    }

    /**
     * 
     * @return list of fuel types
     */
    public List<FuelType> getFuelTypes() {
        String jpql = "select ft from FuelTypes ft order by ft.name";
        TypedQuery<FuelType> query = entityManager.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;
    }

}