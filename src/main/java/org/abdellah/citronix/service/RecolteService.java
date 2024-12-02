package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.request.RecolteRequestDTO;
import org.abdellah.citronix.DTO.response.RecolteResponseDTO;
import org.abdellah.citronix.model.Saison;

import java.util.List;

public interface RecolteService {
    RecolteResponseDTO createRecolte(RecolteRequestDTO dto);
    RecolteResponseDTO updateRecolte(Long id, RecolteRequestDTO dto);
    void deleteRecolte(Long id);
//    List<RecolteResponseDTO> getRecoltesByChampId(Long champId);
    RecolteResponseDTO getRecolteById(Long id);
    List<RecolteResponseDTO> getRecolteBySaison(Saison saison);
    double calculateQuantiteTotale(Long id);






}