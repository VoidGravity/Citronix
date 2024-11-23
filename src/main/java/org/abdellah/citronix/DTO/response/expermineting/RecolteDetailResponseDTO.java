package org.abdellah.citronix.DTO.response.expermineting;

import java.time.LocalDate;
import lombok.Builder;

@Builder
public record RecolteDetailResponseDTO(
        Long id,
        LocalDate dateRecolte,
        double quantite,
        String saison,
        String qualite,
        double rendement,
        ArbreDetailResponseDTO arbre,
        List<VenteDetailResponseDTO> ventes
) {}