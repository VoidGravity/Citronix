package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.ArbreRequestDTO;
import org.abdellah.citronix.DTO.response.ArbreResponseDTO;
import org.abdellah.citronix.exception.BusinessException;
import org.abdellah.citronix.exception.ResourceNotFoundException;
import org.abdellah.citronix.mapper.ArbreMapper;
import org.abdellah.citronix.model.Arbre;
import org.abdellah.citronix.model.ArbreStatus;
import org.abdellah.citronix.model.Champ;
import org.abdellah.citronix.repository.ArbreRepository;
import org.abdellah.citronix.repository.ChampRepository;
import org.abdellah.citronix.service.ArbreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArbreServiceImpl implements ArbreService {
    private final ArbreRepository arbreRepository;
    private final ChampRepository champRepository;
    private final ArbreMapper arbreMapper;


    private int getAge(LocalDate datePlantation) {
        return Period.between(datePlantation, LocalDate.now()).getYears();
    }
//    @Override
//    public ArbreResponseDTO createArbre(ArbreRequestDTO dto) {
//        Champ champ = champRepository.findById(dto.champId())
//                .orElseThrow(() -> new ResourceNotFoundException("Champ", dto.champId()));
//
//        Month plantingMonth = dto.datePlantation().getMonth();
//        if (plantingMonth != Month.MARCH && plantingMonth != Month.APRIL && plantingMonth != Month.MAY) {
//            throw new BusinessException("Les arbres ne peuvent être plantés qu'entre mars et mai");
//        }
//
//        int currentTrees = arbreRepository.countByChampId(dto.champId());
//        int maxTrees = (int) (champ.getSuperficie() * 100); // 100 trees per hectare
//        if (currentTrees >= maxTrees) {
//            throw new BusinessException("Capacité maximale d'arbres atteinte pour ce champ");
//        }
//
//        Arbre arbre = arbreMapper.toEntity(dto);
//
//        return arbreMapper.toDTO(arbreRepository.save(arbre));
//    }
@Override
public ArbreResponseDTO createArbre(ArbreRequestDTO dto) {
    Champ champ = champRepository.findById(dto.champId())
            .orElseThrow(() -> new ResourceNotFoundException("Champ", dto.champId()));

    Month plantingMonth = dto.datePlantation().getMonth();
    if (plantingMonth != Month.MARCH && plantingMonth != Month.APRIL && plantingMonth != Month.MAY) {
        throw new BusinessException("Les arbres ne peuvent être plantés qu'entre mars et mai");
    }

    int currentTrees = arbreRepository.countByChampId(dto.champId());
    int maxTrees = (int) (champ.getSuperficie() * 100);
    if (currentTrees >= maxTrees) {
        throw new BusinessException("Capacité maximale d'arbres atteinte pour ce champ");
    }

    Arbre arbre = arbreMapper.toEntity(dto);

    arbre.setChamp(champ);

    int age = getAge(dto.datePlantation());
    ArbreStatus status;
    if (age < 3) {
        status = ArbreStatus.JEUNE;
    } else if (age <= 10) {
        status = ArbreStatus.MATURE;
    } else if (age < 20) {
        status = ArbreStatus.VIEUX;
    } else {
        status = ArbreStatus.NON_PRODUCTIF;
    }
    arbre.setStatus(status);

    Arbre savedArbre = arbreRepository.save(arbre);
    return arbreMapper.toDTO(savedArbre);
}

    @Override
    public ArbreResponseDTO updateArbre(Long id, ArbreRequestDTO dto) {
        Arbre existingArbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre", id));


        if (getAge(existingArbre.getDatePlantation()) >= 20) {
            throw new BusinessException("Un arbre de plus de 20 ans ne peut pas être modifié");
        }

        arbreMapper.updateEntityFromDTO(dto, existingArbre);
        return arbreMapper.toDTO(arbreRepository.save(existingArbre));
    }

    @Override
    public void deleteArbre(Long id) {
        if (!arbreRepository.existsById(id)) {
            throw new ResourceNotFoundException("Arbre", id);
        }
        arbreRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArbreResponseDTO> getArbresByChampId(Long champId) {
        return arbreRepository.findByChampId(champId).stream()
                .map(arbreMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<ArbreResponseDTO> getAllArbres(){
        return arbreRepository.getAllArbresWithRecolte();
    }

    @Override
    @Transactional(readOnly = true)
    public ArbreResponseDTO getArbreById(Long id) {
        return arbreRepository.findById(id)
                .map(arbreMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre", id));
    }


    @Override
    @Transactional(readOnly = true)
    public double calculateProductivite(Long id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre", id));

        int age = getAge(arbre.getDatePlantation());

        if (age >= 20) return 0;
        if (age < 3) return 2.5;
        if (age <= 10) return 12.0;
        return 20.0;
    }

//    private int calculateAge(LocalDate datePlantation) {
//        return Period.between(datePlantation, LocalDate.now()).getYears();
//    }
    @Override
    @Transactional(readOnly = true)
    public int calculateAge(Long id) {
        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Arbre", id));
        return getAge(arbre.getDatePlantation());
    }
    @Override
    @Transactional(readOnly = true)
    public int countArbresByChamp(Long champId) {
        return arbreRepository.countByChampId(champId);
    }

}