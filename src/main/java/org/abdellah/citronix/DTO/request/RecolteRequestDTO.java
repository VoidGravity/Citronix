package org.abdellah.citronix.DTO.request;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;
import org.abdellah.citronix.model.Saison;

import java.time.LocalDate;


public record RecolteRequestDTO(
        @NotNull(message = "La saison est obligatoire")
        Saison saison,

        @NotNull(message = "La date de récolte est obligatoire")
        @PastOrPresent(message = "La date de récolte ne peut pas être dans le futur")
        LocalDate dateRecolte,

        @NotNull(message = "L'ID du champ est obligatoire")
        Long champId
) {}