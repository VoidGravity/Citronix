package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.model.Vente;
import org.abdellah.citronix.DTO.request.VenteRequestDTO;
import org.abdellah.citronix.DTO.response.VenteResponseDTO;
import org.abdellah.citronix.exception.*;
import org.abdellah.citronix.mapper.VenteMapper;
import org.abdellah.citronix.repository.VenteRepository;
import org.abdellah.citronix.service.VenteService;
import org.abdellah.citronix.validation.VenteValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VenteServiceImpl implements VenteService {
    private final VenteRepository venteRepository;
    private final VenteMapper venteMapper;
    private final VenteValidator venteValidator;

    @Override
    public VenteResponseDTO createVente(VenteRequestDTO request) {
        venteValidator.validate(request);
        Vente vente = venteMapper.toEntity(request);
        return venteMapper.toDTO(venteRepository.save(vente));
    }

    @Transactional
    public VenteResponseDTO updateVente(Long id, VenteRequestDTO request) {
        venteValidator.validate(request);
        Vente vente = venteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente", id));
        venteMapper.updateEntityFromDTO(request, vente);
        return venteMapper.toDTO(vente);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public VenteResponseDTO getVenteById(Long id) {
//        return venteRepository
//                .findById(id)
//                .map(venteMapper::toDTO)
//                .orElseThrow(() -> new ResourceNotFoundException("Vente", id));
//    }
    @Override
    @Transactional(readOnly = true)
    public VenteResponseDTO getVenteById(Long id) {
        Vente vente = venteRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente", id));

        VenteResponseDTO baseDTO = venteMapper.toDTO(vente);

        Double revenuTotal = calculateRevenuTotal(vente);

        return new VenteResponseDTO(
                baseDTO.id(),
                baseDTO.dateVente(),
                baseDTO.prixUnitaire(),
                baseDTO.client(),
                revenuTotal
        );
    }
    private Double calculateRevenuTotal(Vente vente) {

        if (vente.getPrixUnitaire() == null || vente.getRecolte() == null) {
            return 0.0;
        }
        Double quantiteTotale = vente.getRecolte().getQuantiteTotale();

        return quantiteTotale * vente.getPrixUnitaire();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VenteResponseDTO> getAllVentes() {
        return venteRepository.findAll().stream()
                .map(venteMapper::toDTO)
                .toList();
    }



    @Override
    @Transactional(readOnly = true)
    public List<VenteResponseDTO> getVentesByRecolte(Long recolteId) {
        return venteRepository.findByRecolteId(recolteId).stream()
                .map(venteMapper::toDTO)
                .toList();
    }

    @Override
    public void deleteVente(Long id) {
        if (!venteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vente", id);
        }
        venteRepository.deleteById(id);
    }
}