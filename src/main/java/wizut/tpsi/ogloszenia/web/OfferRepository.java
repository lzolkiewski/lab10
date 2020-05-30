package wizut.tpsi.ogloszenia.web;

import org.springframework.data.jpa.repository.JpaRepository;

import wizut.tpsi.ogloszenia.jpa.Offer;

public interface OfferRepository extends JpaRepository<Offer, Integer> {

}