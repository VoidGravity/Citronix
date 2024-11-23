package org.abdellah.citronix.repository;

import org.abdellah.citronix.model.Recolte;
import org.abdellah.citronix.model.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    List<Recolte> findBySaison(Saison saison);
}
