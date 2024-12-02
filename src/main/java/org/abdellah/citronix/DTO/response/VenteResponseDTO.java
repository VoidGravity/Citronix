package org.abdellah.citronix.DTO.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record VenteResponseDTO(
        Long id,
        LocalDate dateVente,
        Double prixUnitaire,
        String client,
        Double revenuTotal
//        Long recolteId
) {}