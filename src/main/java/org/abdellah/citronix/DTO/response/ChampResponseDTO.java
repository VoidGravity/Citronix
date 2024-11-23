package org.abdellah.citronix.DTO.response;

import lombok.Builder;

@Builder
public record ChampResponseDTO(
        Long id,
        String nom,
        Double superficie,
        int nombreArbres,
        int capaciteMaxArbres,
        Double tauxOccupation,
        Long fermeId
) {}
