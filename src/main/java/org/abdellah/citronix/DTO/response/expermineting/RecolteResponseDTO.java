package org.abdellah.citronix.DTO.response.expermineting;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record RecolteResponseDTO(
        Long id,
        LocalDate dateRecolte,
        double quantite,
        String saison,
        String qualite,
        ArbreSimpleResponseDTO arbre,
        List<VenteSimpleResponseDTO> ventes
) {}

