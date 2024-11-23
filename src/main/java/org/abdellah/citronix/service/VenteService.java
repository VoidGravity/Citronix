
package org.abdellah.citronix.service;

import org.abdellah.citronix.DTO.request.VenteRequestDTO;
import org.abdellah.citronix.DTO.response.VenteResponseDTO;
import java.util.List;

public interface VenteService {
    VenteResponseDTO createVente(VenteRequestDTO request);
    VenteResponseDTO getVenteById(Long id);
    List<VenteResponseDTO> getAllVentes();
    List<VenteResponseDTO> getVentesByRecolte(Long recolteId);
    void deleteVente(Long id);
}