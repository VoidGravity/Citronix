package org.abdellah.citronix.DTO.request;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;
@Builder

public record FermeRequestDTO(
        @NotBlank(message = "Le nom de la ferme est obligatoire")
        String nom,

        @NotBlank(message = "La localisation est obligatoire")
        String localisation,

        @NotNull(message = "La superficie est obligatoire")
        @Positive(message = "La superficie doit être positive")
        @DecimalMin(value = "0.1", message = "La superficie minimale est de 0.1 hectare")
        Double superficie,

        @NotNull(message = "La date de création est obligatoire")
        @PastOrPresent(message = "La date de création ne peut pas être dans le futur")
        LocalDate dateCreation
) {}

