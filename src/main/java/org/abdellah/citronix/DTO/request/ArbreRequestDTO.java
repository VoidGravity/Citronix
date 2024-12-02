package org.abdellah.citronix.DTO.request;


import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record ArbreRequestDTO(
//        @NotBlank(message = "Le nom de l'arbre est obligatoire")
//        String nom,

        @NotNull(message = "La date de plantation est obligatoire")
        @PastOrPresent(message = "La date de plantation ne peut pas être dans le futur")
        LocalDate datePlantation,

        @NotNull(message = "L'ID du champ est obligatoire")
        Long champId
) {}
