package org.abdellah.citronix.repository;


import org.abdellah.citronix.model.RecolteDetail;
import org.abdellah.citronix.model.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecolteDetailRepository extends JpaRepository<RecolteDetail, Long> {
    List<RecolteDetail> findByRecolteId(Long recolteId);
    List<RecolteDetail> findByArbreId(Long arbreId);

    @Query("SELECT COUNT(rd) FROM RecolteDetail rd WHERE rd.arbre.id = :arbreId AND rd.recolte.saison = :saison")
    int countByArbreIdAndSaison(
            @Param("arbreId") Long arbreId,
            @Param("saison") Saison saison
    );
}

