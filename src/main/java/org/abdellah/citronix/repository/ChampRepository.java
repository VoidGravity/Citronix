package org.abdellah.citronix.repository;

import org.abdellah.citronix.model.Champ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampRepository extends JpaRepository<Champ, Long> {
    List<Champ> findByFermeId(Long fermeId);
    //    void deleteByFermeId(Long fermeId);

    int countByFermeId(Long fermeId);

    @Query("SELECT SUM(c.superficie) FROM Champ c WHERE c.ferme.id = :fermeId")
    Double sumSuperficieByFermeId(@Param("fermeId") Long fermeId);

    @Query("SELECT SUM(c.superficie) FROM Champ c WHERE c.ferme.id = :fermeId AND c.id != :champId")
    Double sumSuperficieByFermeIdExcludingChampId(
            @Param("fermeId") Long fermeId,
            @Param("champId") Long champId
    );

}