package org.abdellah.citronix.repository;

import org.abdellah.citronix.DTO.response.ArbreResponseDTO;
import org.abdellah.citronix.model.Arbre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ArbreRepository extends JpaRepository<Arbre, Long> {
    //qeury deriver
    List<Arbre> findByChampId(Long champId);
    int countByChampId(Long champId);
    @Query("SELECT a from Arbre a LEFT JOIN RecolteDetail LEFT JOIN Recolte")
    List<ArbreResponseDTO> getAllArbresWithRecolte();
    //HQL
    //->
//    @Query("SELECT COUNT(a) FROM Arbre a WHERE a.champ.id = :champId AND a.datePlantation <= :date")
//
//    int countArbresByChampIdAndDatePlantationBefore(
//            @Param("champId") Long champId,
//            @Param("date") LocalDate date
//    );
}