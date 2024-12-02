package org.abdellah.citronix.repository;

import org.abdellah.citronix.model.Recolte;
import org.abdellah.citronix.model.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecolteRepository extends JpaRepository<Recolte, Long> {
    List<Recolte> findBySaison(Saison saison);
//    boolean existsByChampIdAndSaison(Long champId, Saison saison);


//    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Recolte r " +
//            "JOIN r.recolteDetails rd " +
//            "JOIN rd.arbre a " +
//            "WHERE a.champ.id = :champId AND r.saison = :saison")
//    boolean existsByChampAndSaison(@Param("champId") Long champId, @Param("saison") Saison saison);
}


