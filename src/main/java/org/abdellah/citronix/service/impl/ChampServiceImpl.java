package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.ChampRequestDTO;
import org.abdellah.citronix.DTO.response.ChampResponseDTO;
import org.abdellah.citronix.mapper.ChampMapper;
import org.abdellah.citronix.model.Champ;
import org.abdellah.citronix.model.Ferme;
import org.abdellah.citronix.repository.ChampRepository;
import org.abdellah.citronix.repository.FermeRepository;
import org.abdellah.citronix.service.ChampService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChampServiceImpl implements ChampService {
    private final ChampRepository champRepository;
    private final FermeRepository fermeRepository;
    private final ChampMapper champMapper;

    @Override
    public ChampResponseDTO createChamp(ChampRequestDTO dto) {
        Ferme ferme = fermeRepository.findById(dto.fermeId())
                .orElseThrow(() -> new ResourceNotFoundException("Ferme", dto.fermeId()));

        // Verify field constraints
        if (dto.superficie() < 0.1) {
            throw new BusinessException("La superficie minimale d'un champ est de 0.1 hectare");
        }

        if (champRepository.countByFermeId(dto.fermeId()) >= 10) {
            throw new BusinessException("Une ferme ne peut pas avoir plus de 10 champs");
        }

        double totalSuperficie = champRepository.sumSuperficieByFermeId(dto.fermeId());
        if ((totalSuperficie + dto.superficie()) > ferme.getSuperficie() * 0.5) {
            throw new BusinessException("La superficie totale des champs ne peut pas dépasser 50% de la superficie de la ferme");
        }

        Champ champ = champMapper.toEntity(dto);
        return champMapper.toDTO(champRepository.save(champ));
    }

}