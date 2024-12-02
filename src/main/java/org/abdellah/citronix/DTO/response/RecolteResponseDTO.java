package org.abdellah.citronix.DTO.response;

import lombok.Builder;
import org.abdellah.citronix.model.Saison;

import java.time.LocalDate;
import java.util.List;


@Builder
public record RecolteResponseDTO(
        Long id,
        Saison saison,
        LocalDate dateRecolte,
        Double quantiteTotale
//        Long champId

) {}