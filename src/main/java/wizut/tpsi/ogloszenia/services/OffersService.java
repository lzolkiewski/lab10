package wizut.tpsi.ogloszenia.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;

@Service
public class OffersService {
    @PersistenceContext
    private EntityManager entityManager;

    public CarManufacturer getCarManufacturer(int id) {
        return entityManager.find(CarManufacturer.class, id);
    }

    public CarModel getCarModel(int id) {
        return entityManager.find(CarModel.class, id);
    }
}