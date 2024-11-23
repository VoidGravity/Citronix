package org.abdellah.citronix.DTO.response;

import lombok.Builder;

@Builder
public record VenteResponseDTO(
        Long id,
        LocalDate dateVente,
        Double prixUnitaire,
        String client,
        Double revenuTotal,
        Long recolteId
) {}