package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.RecolteRequestDTO;
import org.abdellah.citronix.DTO.response.RecolteResponseDTO;
import org.abdellah.citronix.exception.BusinessException;
import org.abdellah.citronix.exception.ResourceNotFoundException;
import org.abdellah.citronix.mapper.RecolteMapper;
import org.abdellah.citronix.model.Recolte;
import org.abdellah.citronix.model.Saison;
import org.abdellah.citronix.repository.RecolteRepository;
import org.abdellah.citronix.service.RecolteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class RecolteServiceImpl implements RecolteService {
    private final RecolteRepository recolteRepository;
    private final RecolteMapper recolteMapper;

    @Override
    public RecolteResponseDTO createRecolte(RecolteRequestDTO request) {
        Recolte recolte = recolteMapper.toEntity(request);
        return recolteMapper.toDTO(recolteRepository.save(recolte));
    }

    @Override
    public RecolteResponseDTO updateRecolte(Long id, RecolteRequestDTO request) {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte", id));

//        if (!existingRecolte.getSeason().equals(request.saison())) {
//          // je veux ajouter la valider later, make sure to chec k saison
//        }

        recolteMapper.updateEntityFromDTO(request, existingRecolte);
        return recolteMapper.toDTO(recolteRepository.save(existingRecolte));
    }

    @Override
    public void deleteRecolte(Long id) {
        if (!recolteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recolte", id);
        }
        recolteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RecolteResponseDTO getRecolteById(Long id) {
        return recolteRepository.findById(id)
                .map(recolteMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte", id));
    }

//    @Override
//    @Transactional(readOnly = true)
//    public List<RecolteResponseDTO> getRecoltesByChampId(Long champId) {
//        return recolteRepository.findByChampId(champId).stream()
//                .map(recolteMapper::toDTO)
//                .toList();
//    }

    @Override
    @Transactional(readOnly = true)
    public List<RecolteResponseDTO> getRecolteBySaison(Saison saison) {
        return recolteRepository.findBySaison(saison).stream()
                .map(recolteMapper::toDTO)
                .toList();
    }
    @Override
    @Transactional(readOnly = true)
    public double calculateQuantiteTotale(Long id) {
        Recolte recolte = recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte", id));
        return recolte.getQuantiteTotale();
    }



}