package org.abdellah.citronix.DTO.response;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ArbreResponseDTO(
        Long id,
        String nom,
        LocalDate datePlantation,
        int age,
        String status,
        Double productiviteParSaison,
        Long champId
) {}