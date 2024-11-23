package org.abdellah.citronix.service.impl;

import lombok.RequiredArgsConstructor;
import org.abdellah.citronix.DTO.response.FermeViewModel;
import org.abdellah.citronix.model.Ferme;
import org.abdellah.citronix.repository.FermeRepository;
import org.abdellah.citronix.service.FermeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FermeServiceImpl implements FermeService {
    private final FermeRepository fermeRepository;
    private final FermeViewMapper fermeViewMapper;

    @Override
    public FermeViewModel createFerme(FermeViewModel fermeVM) {
        Ferme ferme = Ferme.builder()
                .nom(fermeVM.getNom())
                .localisation(fermeVM.getLocalisation())
                .superficie(fermeVM.getSuperficie())
                .dateCreation(LocalDate.now())
                .build();

        return fermeViewMapper.toViewModel(fermeRepository.save(ferme));
    }
    // Other methods...
}