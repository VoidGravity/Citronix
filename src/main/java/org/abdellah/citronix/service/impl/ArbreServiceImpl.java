package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.response.ArbreResponseDTO;
import org.abdellah.citronix.mapper.ArbreMapper;
import org.abdellah.citronix.model.Arbre;
import org.abdellah.citronix.model.Champ;
import org.abdellah.citronix.repository.ChampRepository;
import org.abdellah.citronix.service.ArbreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;

@Service
@RequiredArgsConstructor
@Transactional
public class ArbreServiceImpl implements ArbreService {
    private final ArbreRepository arbreRepository;
    private final ChampRepository champRepository;
    private final ArbreMapper arbreMapper;

    @Override
    public ArbreResponseDTO createArbre(ArbreRequestDTO dto) {
        Champ champ = champRepository.findById(dto.champId())
                .orElseThrow(() -> new ResourceNotFoundException("Champ", dto.champId()));

        // Verify planting period
        Month plantingMonth = dto.datePlantation().getMonth();
        if (plantingMonth != Month.MARCH && plantingMonth != Month.APRIL && plantingMonth != Month.MAY) {
            throw new BusinessException("Les arbres ne peuvent être plantés qu'entre mars et mai");
        }

        // Verify field capacity
        int currentTrees = arbreRepository.countByChampId(dto.champId());
        int maxTrees = (int) (champ.getSuperficie() * 100); // 100 trees per hectare
        if (currentTrees >= maxTrees) {
            throw new BusinessException("Capacité maximale d'arbres atteinte pour ce champ");
        }

        Arbre arbre = arbreMapper.toEntity(dto);
        return arbreMapper.toDTO(arbreRepository.save(arbre));
    }

    // Other implemented methods...
}