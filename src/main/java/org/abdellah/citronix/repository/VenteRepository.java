package org.abdellah.citronix.repository;

import org.abdellah.citronix.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VenteRepository extends JpaRepository<Vente, Long> {
    List<Vente> findByRecolteId(Long recolteId);
}
