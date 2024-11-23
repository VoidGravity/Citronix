package org.abdellah.citronix.DTO.request;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


public record VenteRequestDTO(
        @NotNull(message = "La date de vente est obligatoire")
        @PastOrPresent(message = "La date de vente ne peut pas être dans le futur")
        LocalDate dateVente,

        @NotNull(message = "Le prix unitaire est obligatoire")
        @Positive(message = "Le prix unitaire doit être positif")
        Double prixUnitaire,

        @NotBlank(message = "Le client est obligatoire")
        String client,

        @NotNull(message = "L'ID de la récolte est obligatoire")
        Long recolteId
) {}