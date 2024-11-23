package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.FermeRequestDTO;
import org.abdellah.citronix.DTO.response.FermeResponseDTO;
import org.abdellah.citronix.mapper.FermeMapper;
import org.abdellah.citronix.model.Ferme;
import org.abdellah.citronix.repository.ChampRepository;
import org.abdellah.citronix.repository.FermeRepository;
import org.abdellah.citronix.service.FermeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FermeServiceImpl implements FermeService {
    private final FermeRepository fermeRepository;
    private final FermeMapper fermeMapper;

    @Override
    public FermeResponseDTO createFerme(FermeRequestDTO dto) {
        Ferme ferme = fermeMapper.toEntity(dto);
        return fermeMapper.toDTO(fermeRepository.save(ferme));
    }

    @Override
    public FermeResponseDTO updateFerme(Long id, FermeRequestDTO dto) {
        Ferme existingFerme = fermeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme", id));

        fermeMapper.updateEntityFromDTO(dto, existingFerme);
        return fermeMapper.toDTO(fermeRepository.save(existingFerme));
    }

    @Override
    public void deleteFerme(Long id) {
        if (!fermeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ferme", id);
        }
        fermeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FermeResponseDTO> getAllFermes() {
        return fermeRepository.findAll().stream()
                .map(fermeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FermeResponseDTO getFermeById(Long id) {
        return fermeRepository.findById(id)
                .map(fermeMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Ferme", id));
    }
}