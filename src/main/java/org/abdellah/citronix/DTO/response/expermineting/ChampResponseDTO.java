package org.abdellah.citronix.DTO.response.expermineting;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record ChampResponseDTO(
        Long id,
        String nom,
        double superficie,
        LocalDate dateCreation,
        int nombreArbres,
        double productiviteMoyenne,
        FermeSimpleResponseDTO ferme,
        List<ArbreSimpleResponseDTO> arbres,
        List<StatistiquesChampResponseDTO> statistiques
) {}