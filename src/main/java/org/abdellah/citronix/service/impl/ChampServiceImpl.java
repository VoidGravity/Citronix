package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.ChampRequestDTO;
import org.abdellah.citronix.DTO.response.ChampResponseDTO;
import org.abdellah.citronix.exception.BusinessException;
import org.abdellah.citronix.exception.ResourceNotFoundException;
import org.abdellah.citronix.mapper.ChampMapper;
import org.abdellah.citronix.model.Champ;
import org.abdellah.citronix.model.Ferme;
import org.abdellah.citronix.repository.ChampRepository;
import org.abdellah.citronix.repository.FermeRepository;
import org.abdellah.citronix.service.ChampService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

        validateChampCreation(dto, ferme);

        Champ champ = champMapper.toEntity(dto);
        return champMapper.toDTO(champRepository.save(champ));
    }

    @Override
    public ChampResponseDTO updateChamp(Long id, ChampRequestDTO dto) {
        Champ existingChamp = champRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Champ", id));

        validateChampUpdate(dto, existingChamp);

        champMapper.updateEntityFromDTO(dto, existingChamp);
        return champMapper.toDTO(champRepository.save(existingChamp));
    }

    @Override
    public void deleteChamp(Long id) {
        if (!champRepository.existsById(id)) {
            throw new ResourceNotFoundException("Champ", id);
        }
        champRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChampResponseDTO> getChampsByFermeId(Long fermeId) {
        return champRepository.findByFermeId(fermeId).stream()
                .map(champMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ChampResponseDTO getChampById(Long id) {
        return champRepository.findById(id)
                .map(champMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Champ", id));
    }

    @Override
    @Transactional(readOnly = true)
    public int calculateCapaciteArbres(Long id) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Champ", id));

        return (int) (champ.getSuperficie() * 100);
    }

//    private void validateChampCreation(ChampRequestDTO dto, Ferme ferme) {
//        if (dto.superficie() < 0.1) {
//            throw new BusinessException("La superficie minimale d'un champ est de 0.1 hectare");
//        }
//
//        if (champRepository.countByFermeId(dto.fermeId()) >= 10) {
//            throw new BusinessException("Une ferme ne peut pas avoir plus de 10 champs");
//        }
//
//        double totalSuperficie = champRepository.sumSuperficieByFermeId(dto.fermeId());
//        if ((totalSuperficie + dto.superficie()) > ferme.getSuperficie() * 0.5) {
//            throw new BusinessException("La superficie totale des champs ne peut pas dépasser 50% de la superficie de la ferme");
//        }
//    }
private void validateChampCreation(ChampRequestDTO dto, Ferme ferme) {
    if (dto.superficie() < 0.1) {
        throw new BusinessException("La superficie minimale d'un champ est de 0.1 hectare");
    }

    if (champRepository.countByFermeId(dto.fermeId()) >= 10) {
        throw new BusinessException("Une ferme ne peut pas avoir plus de 10 champs");
    }

    Double currentTotalSuperficie = champRepository.sumSuperficieByFermeId(dto.fermeId());
    double totalSuperficie = (currentTotalSuperficie == null ? 0.0 : currentTotalSuperficie) + dto.superficie();

    if (totalSuperficie > ferme.getSuperficie() * 1) {
//    if (totalSuperficie > ferme.getSuperficie() * 0.5) {
        throw new BusinessException(
                String.format("La superficie totale des champs (%f) ne peut pas dépasser 50%% de la superficie de la ferme (%f)",
                        totalSuperficie, ferme.getSuperficie())
        );
    }

}

    private void validateChampUpdate(ChampRequestDTO dto, Champ existingChamp) {
        if (dto.superficie() < 0.1) {
            throw new BusinessException("La superficie minimale d'un champ est de 0.1 hectare");
        }

        double totalSuperficieAutresChamps = champRepository.sumSuperficieByFermeIdExcludingChampId(
                existingChamp.getFerme().getId(), existingChamp.getId());

        if ((totalSuperficieAutresChamps + dto.superficie()) > existingChamp.getFerme().getSuperficie() * 0.5) {
            throw new BusinessException("La superficie totale des champs ne peut pas dépasser 50% de la superficie de la ferme");
        }
    }
}
