package org.abdellah.citronix.DTO.request;

import jakarta.validation.constraints.*;

public record ChampRequestDTO(
        @NotBlank(message = "Le nom du champ est obligatoire")
        String nom,

        @NotNull(message = "La superficie est obligatoire")
        @DecimalMin(value = "0.1", message = "La superficie minimale est de 0.1 hectare")
        Double superficie,

        @NotNull(message = "L'ID de la ferme est obligatoire")
        Long fermeId
) {}