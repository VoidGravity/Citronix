package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.request.ChampRequestDTO;
import org.abdellah.citronix.DTO.response.ChampResponseDTO;

import java.util.List;

public interface ChampService {
    ChampResponseDTO createChamp(ChampRequestDTO dto);
    ChampResponseDTO updateChamp(Long id, ChampRequestDTO dto);
    void deleteChamp(Long id);
    List<ChampResponseDTO> getChampsByFermeId(Long fermeId);
    ChampResponseDTO getChampById(Long id);
    int calculateCapaciteArbres(Long id);
}
