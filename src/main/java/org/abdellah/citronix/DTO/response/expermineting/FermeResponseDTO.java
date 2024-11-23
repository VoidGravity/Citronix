package org.abdellah.citronix.DTO.response.expermineting;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record FermeResponseDTO(
        Long id,
        String nom,
        String localisation,
        double superficie,
        LocalDate dateCreation,
        int nombreChamps,
        int nombreArbresTotal,
        double productiviteTotale,
        List<ChampSimpleResponseDTO> champs,
        List<PerformanceResponseDTO> performances
) {}