package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.request.ArbreRequestDTO;
import org.abdellah.citronix.DTO.response.ArbreResponseDTO;

import java.util.List;

public interface ArbreService {
    ArbreResponseDTO createArbre(ArbreRequestDTO dto);

    ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO dto);
    void deleteArbre(Long id);
    List<ArbreResponseDTO> getArbresByChampId(Long champId);
    ArbreResponseDTO getArbreById(Long id);
    double calculateProductivite(Long id);
}
