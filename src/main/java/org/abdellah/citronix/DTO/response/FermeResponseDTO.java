package org.abdellah.citronix.DTO.response;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record FermeResponseDTO(
        Long id,
        String nom,
        String localisation,
        Double superficie,
        LocalDate dateCreation,
        Double superficieDisponible,
        int nombreChamps
) {}