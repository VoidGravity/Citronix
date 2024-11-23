package org.abdellah.citronix.DTO.response;

import lombok.Builder;

@Builder
public record RecolteDetailResponseDTO(
        Long id,
        Double quantite,
        Long recolteId,
        Long arbreId
) {}