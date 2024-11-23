package org.abdellah.citronix.repository;

import org.abdellah.citronix.model.Ferme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FermeRepository extends JpaRepository<Ferme, Long>, JpaSpecificationExecutor<Ferme> {

}
