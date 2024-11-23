package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.RecolteRequestDTO;
import org.abdellah.citronix.DTO.response.RecolteResponseDTO;
import org.abdellah.citronix.exception.BusinessException;
import org.abdellah.citronix.exception.ResourceNotFoundException;
import org.abdellah.citronix.mapper.RecolteMapper;
import org.abdellah.citronix.model.Recolte;
import org.abdellah.citronix.model.Saison;
import org.abdellah.citronix.repository.ChampRepository;
import org.abdellah.citronix.repository.RecolteRepository;
import org.abdellah.citronix.service.RecolteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class RecolteServiceImpl implements RecolteService {
    private final RecolteRepository recolteRepository;
    private final ChampRepository champRepository;
    private final RecolteMapper recolteMapper;

    @Override
    public RecolteResponseDTO createRecolte(RecolteRequestDTO dto) {
        validateRecolteCreation(dto);

        Recolte recolte = recolteMapper.toEntity(dto);
        return recolteMapper.toDTO(recolteRepository.save(recolte));
    }

    @Override
    public RecolteResponseDTO updateRecolte(Long id, RecolteRequestDTO dto) {
        Recolte existingRecolte = recolteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte", id));

        validateRecolteUpdate(dto, existingRecolte);

        recolteMapper.updateEntityFromDTO(dto, existingRecolte);
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
    public List<RecolteResponseDTO> getRecoltesByChampId(Long champId) {
        return recolteRepository.findByChampId(champId).stream()
                .map(recolteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public RecolteResponseDTO getRecolteById(Long id) {
        return recolteRepository.findById(id)
                .map(recolteMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Recolte", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecolteResponseDTO> getRecolteBySaison(Saison saison) {
        return recolteRepository.findBySaison(saison).stream()
                .map(recolteMapper::toDTO)
                .collect(Collectors.toList());
    }

    private void validateRecolteCreation(RecolteRequestDTO dto) {
        champRepository.findById(dto.champId())
                .orElseThrow(() -> new ResourceNotFoundException("Champ", dto.champId()));

        if (recolteRepository.existsByChampIdAndSaison(dto.champId(), dto.saison())) {
            throw new BusinessException("Une seule récolte par saison est autorisée pour un champ");
        }
    }

    private void validateRecolteUpdate(RecolteRequestDTO dto, Recolte existingRecolte) {

        if (!existingRecolte.getChamp().getId().equals(dto.champId()) ||

                !existingRecolte.getSaison().equals(dto.saison())) {
            if (recolteRepository.existsByChampIdAndSaison(dto.champId(), dto.saison())) {
                throw new BusinessException("Une seule récolte par saison est autorisée pour un champ");
            }
        }
    }
}