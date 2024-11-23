package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.request.FermeRequestDTO;
import org.abdellah.citronix.DTO.response.FermeResponseDTO;

import java.util.List;

public interface FermeService {
    FermeResponseDTO createFerme(FermeRequestDTO dto);
    FermeResponseDTO updateFerme(Long id, FermeRequestDTO dto);
    void deleteFerme(Long id);
    List<FermeResponseDTO> getAllFermes();
    FermeResponseDTO getFermeById(Long id);
}