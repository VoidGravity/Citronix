package org.abdellah.citronix.DTO.response.expermineting;

import org.abdellah.citronix.model.ArbreStatus;
import java.time.LocalDate;
import lombok.Builder;

@Builder
public record ArbreResponseDTO(
        Long id,
        String nom,
        LocalDate datePlantation,
        int age,
        ArbreStatus status,
        double productiviteParSaison,
        ChampSimpleResponseDTO champ
) {}