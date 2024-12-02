package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.request.ArbreRequestDTO;
import org.abdellah.citronix.DTO.response.ArbreResponseDTO;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ArbreService {
    ArbreResponseDTO createArbre(ArbreRequestDTO dto);
    int calculateAge(Long id);
    int countArbresByChamp(Long champId);
    //here is where the thing is going to be
    //over here not 
    //
    ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO dto);
    void deleteArbre(Long id);
    List<ArbreResponseDTO> getArbresByChampId(Long champId);
    ArbreResponseDTO getArbreById(Long id);
    double calculateProductivite(Long id);
    List<ArbreResponseDTO> getAllArbres();

}
