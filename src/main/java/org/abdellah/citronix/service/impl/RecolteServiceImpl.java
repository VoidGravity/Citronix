package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.request.RecolteRequestDTO;
import org.abdellah.citronix.DTO.response.RecolteResponseDTO;
import org.abdellah.citronix.exception.BusinessException;
import org.abdellah.citronix.exception.ResourceNotFoundException;
import org.abdellah.citronix.mapper.RecolteMapper;
import org.abdellah.citronix.model.*;
import org.abdellah.citronix.repository.ArbreRepository;
import org.abdellah.citronix.repository.ChampRepository;
import org.abdellah.citronix.repository.RecolteDetailRepository;
import org.abdellah.citronix.repository.RecolteRepository;
import org.abdellah.citronix.service.RecolteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional

public class RecolteServiceImpl implements RecolteService {
    private final RecolteRepository recolteRepository;
    private final RecolteMapper recolteMapper;
    private final ArbreRepository arbreRepository;
    private final RecolteDetailRepository recolteDetailRepository;




    //    @Override
//    public RecolteResponseDTO createRecolte(RecolteRequestDTO request) {
//        Recolte recolte = recolteMapper.toEntity(request);
//        return recolteMapper.toDTO(recolteRepository.save(recolte));
//    }
//    @Override
//    public RecolteResponseDTO createRecolte(RecolteRequestDTO request) {
//        List<Arbre> arbres = arbreRepository.findByChampId(request.champId());
//        if (arbres.isEmpty()) {
//            throw new BusinessException("Aucun arbre trouvé dans ce champ");
//        }
//
//        double quantiteTotale = calculateTotalQuantity(arbres);
//        if (quantiteTotale <= 0) {
//            throw new BusinessException("La quantité totale doit être supérieure à 0");
//        }
//
//
//        Recolte recolte = recolteMapper.toEntity(request);
//        recolte.setQuantiteTotale(quantiteTotale);
//        recolte.setRecolteDetails(new ArrayList<>());
//
//        Recolte savedRecolte = recolteRepository.save(recolte);
//
//        for (Arbre arbre : arbres) {
//            RecolteDetail detail = RecolteDetail.builder()
//                    .recolte(savedRecolte)
//                    .arbre(arbre)
//                    .quantite(calculateTreeQuantity(arbre))
//                    .build();
//
//            RecolteDetail savedDetail = recolteDetailRepository.save(detail);
//            savedRecolte.getRecolteDetails().add(savedDetail);
//        }
//
//        Recolte finalRecolte = recolteRepository.save(savedRecolte);
//        return recolteMapper.toDTO(finalRecolte);
//    }
    @Override
    public RecolteResponseDTO createRecolte(RecolteRequestDTO request) {
        List<Recolte> existingRecoltes = recolteRepository.findBySaison(request.saison());
        for (Recolte existingRecolte : existingRecoltes) {
            for (RecolteDetail detail : existingRecolte.getRecolteDetails()) {
                if (detail.getArbre().getChamp().getId().equals(request.champId())) {
                    throw new BusinessException("Une récolte existe déjà pour ce champ dans cette saison");
                }
            }
        }

        if (!isValidHarvestDate(request.dateRecolte(), request.saison())) {
            throw new BusinessException("La date de récolte ne correspond pas à la saison indiquée");
        }

        List<Arbre> arbres = arbreRepository.findByChampId(request.champId());
        if (arbres.isEmpty()) {
            throw new BusinessException("Aucun arbre trouvé dans ce champ");
        }

        double quantiteTotale = calculateTotalQuantity(arbres);
        if (quantiteTotale <= 0) {
            throw new BusinessException("La quantité totale doit être supérieure à 0");
        }

        Recolte recolte = recolteMapper.toEntity(request);
        recolte.setQuantiteTotale(quantiteTotale);
        recolte.setRecolteDetails(new ArrayList<>());

        Recolte savedRecolte = recolteRepository.save(recolte);

        for (Arbre arbre : arbres) {
            RecolteDetail detail = RecolteDetail.builder()
                    .recolte(savedRecolte)
                    .arbre(arbre)
                    .quantite(calculateTreeQuantity(arbre))
                    .build();

            RecolteDetail savedDetail = recolteDetailRepository.save(detail);
            savedRecolte.getRecolteDetails().add(savedDetail);
        }

        Recolte finalRecolte = recolteRepository.save(savedRecolte);
        return recolteMapper.toDTO(finalRecolte);
    }

    private boolean isValidHarvestDate(LocalDate date, Saison saison) {
        Month month = date.getMonth();
        return switch (saison) {
            case HIVER -> month == Month.DECEMBER || month == Month.JANUARY || month == Month.FEBRUARY;
            case PRINTEMPS -> month == Month.MARCH || month == Month.APRIL || month == Month.MAY;
            case ETE -> month == Month.JUNE || month == Month.JULY || month == Month.AUGUST;
            case AUTOMNE -> month == Month.SEPTEMBER || month == Month.OCTOBER || month == Month.NOVEMBER;
        };
    }

    private RecolteDetail createRecolteDetail(Recolte recolte, Arbre arbre) {
        double quantite = calculateTreeQuantity(arbre);
        return RecolteDetail.builder()
                .recolte(recolte)
                .arbre(arbre)
                .quantite(quantite)
                .build();
    }
    private double calculateTreeQuantity(Arbre arbre) {
        int age = calculateAge(arbre.getDatePlantation());
        if (age >= 20) return 0;
        if (age < 3) return 2.5;
        if (age <= 10) return 12.0;
        return 20.0;
    }

    private double calculateTotalQuantity(List<Arbre> arbres) {
        return arbres.stream()
                .mapToDouble(this::calculateTreeQuantity)
                .sum();
    }

    private int calculateAge(LocalDate datePlantation) {
        return Period.between(datePlantation, LocalDate.now()).getYears();
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