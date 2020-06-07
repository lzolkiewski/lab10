package wizut.tpsi.ogloszenia.services;

import java.util.List;

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
import wizut.tpsi.ogloszenia.web.OfferSorter;

@Service
@Transactional
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
        System.out.println(query);
        return query.getResultList();
    }

    /**
     *
     * @param modelId id of specyfic model
     * @return list of offers by model
     */
    public List<Offer> getOffersByModel(int modelId) {
        String jpql = "select o from Offer o where o.model.id = :id order by o.title";
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
        String jpql = "select o from Offer o where o.model.manufacturer.id = :id order by o.title";
        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        query.setParameter("id", manufacturerId);

        return query.getResultList();
    }

    public List<Offer> getOffersByFuelType(int fuelTypeId) {
        String jpql = "select o from Offer o where o.fuelType.id = :id order by o.title";
        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        query.setParameter("id", fuelTypeId);

        return query.getResultList();
    }

    public List<Offer> getOfferByYear(Integer from, Integer to) {
        if (from != null && to != null) {
            String jpql = "select o from Offer o where o.year > :fr and o.year < :to order by o.title";
            TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
            query.setParameter("fr", from);
            query.setParameter("to", to);
            return query.getResultList();
        } else {
            if (from != null) {
                String jpql = "select o from Offer o where o.year > :fr order by o.title";
                TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
                query.setParameter("fr", from);
                return query.getResultList();
            } else {
                String jpql = "select o from Offer o where o.year < :to order by o.title";
                TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
                query.setParameter("to", to);
                return query.getResultList();
            }
        }

    }

    public List<Offer> getOffers(OfferFilter filter, OfferSorter sorter) {
        String jpql = "select o from Offer o where";
        if (filter.getManufacturerId() != null) {
            jpql += " o.model.manufacturer.id = :manuid";
        }
        if (filter.getModelId() != null) {
            if (filter.getManufacturerId() != null) {
                jpql += " and";
            }
            jpql += " o.model.id = :mdlid";
        }
        if (filter.getYearFrom() != null || filter.getYearTo() != null) {
            if (filter.getManufacturerId() != null || filter.getModelId() != null) {
                jpql += " and";
            }
            if (filter.getYearFrom() != null && filter.getYearTo() != null) {
                jpql += " o.year >= :fr and o.year <= :to";
            } else {
                if (filter.getYearFrom() != null) {
                    jpql += " o.year >= :fr";
                } else {
                    jpql += " o.year <= :to";
                }
            }
        }
        if (filter.getFuelTypeId() != null) {
            if (filter.getManufacturerId() != null || filter.getModelId() != null || filter.getYearFrom() != null
                    || filter.getYearTo() != null) {
                jpql += " and";
            }
            jpql += " o.fuelType.id = :fltid";
        }

        if (sorter.getPrice() != null) {
            jpql += " order by o.price";
        } else if (sorter.getYear() != null) {
            jpql += " order by o.year";
        } else if (sorter.getAddDate() != null) {
            jpql += " order by o.addDate";
        } else {
            jpql += " order by o.title";
        }

        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        if (filter.getManufacturerId() != null)
            query.setParameter("manuid", filter.getManufacturerId());
        if (filter.getModelId() != null)
            query.setParameter("mdlid", filter.getModelId());
        if (filter.getYearFrom() != null)
            query.setParameter("fr", filter.getYearFrom());
        if (filter.getYearTo() != null)
            query.setParameter("to", filter.getYearTo());
        if (filter.getFuelTypeId() != null)
            query.setParameter("fltid", filter.getFuelTypeId());

        return query.getResultList();
    }

    public List<Offer> getOffersByDescription(String desc) {
        String jpql = "select o from Offer o where o.description LIKE '%" + desc + "%'";
        TypedQuery<Offer> query = entityManager.createQuery(jpql, Offer.class);
        return query.getResultList();
    }

    /**
     * @param offer pass offer to add to database
     * @return entity
     */
    public Offer createOffer(Offer offer) {
        entityManager.persist(offer);
        return offer;
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

    public List<Offer> getOffersInOrder(OfferSorter offerSorter) {
        String jpql = "select offer from Offer offer order by offer.title";
        if (offerSorter.getPrice() != null) {
            jpql = "select offer from Offer offer order by offer.price";
        } else if (offerSorter.getYear() != null) {
            jpql = "select offer from Offer offer order by offer.year";
        } else {
            jpql = "select offer from Offer offer order by offer.addDate";
        }
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
        String jpql = "select bs from BodyStyle bs order by bs.name";
        TypedQuery<BodyStyle> query = entityManager.createQuery(jpql, BodyStyle.class);
        List<BodyStyle> result = query.getResultList();
        return result;
    }

    /**
     *
     * @return list of fuel types
     */
    public List<FuelType> getFuelTypes() {
        String jpql = "select ft from FuelType ft order by ft.name";
        TypedQuery<FuelType> query = entityManager.createQuery(jpql, FuelType.class);
        List<FuelType> result = query.getResultList();
        return result;
    }

    /**
     *
     * @param id
     * @return
     */
    public Offer deletOffer(Integer id) {
        Offer offer = entityManager.find(Offer.class, id);
        entityManager.remove(offer);
        return offer;
    }

    public Offer saveOffer(Offer offer) {
        return entityManager.merge(offer);
    }

}