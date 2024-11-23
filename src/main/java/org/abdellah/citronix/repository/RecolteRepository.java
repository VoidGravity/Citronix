package org.abdellah.citronix.repository;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.model.Recolte;
import org.abdellah.citronix.model.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    List<Recolte> findByChampId(Long champId);
    List<Recolte> findBySaison(Saison saison);
    boolean existsByChampIdAndSaison(Long champId, Saison saison);
}
