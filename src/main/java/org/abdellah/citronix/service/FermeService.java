package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.request.FermeRequestDTO;
import org.abdellah.citronix.DTO.response.FermeResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FermeService {
    FermeResponseDTO createFerme(FermeRequestDTO dto);
    FermeResponseDTO updateFerme(Long id, FermeRequestDTO dto);
    void deleteFerme(Long id);
    List<FermeResponseDTO> getAllFermes();
    FermeResponseDTO getFermeById(Long id);
}