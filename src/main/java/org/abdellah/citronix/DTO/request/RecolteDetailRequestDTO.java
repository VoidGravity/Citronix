package org.abdellah.citronix.DTO.request;

import jakarta.validation.constraints.*;


public record RecolteDetailRequestDTO(
        @NotNull(message = "La quantité est obligatoire")
        @Positive(message = "La quantité doit être positive")
        Double quantite,

        @NotNull(message = "L'ID de la récolte est obligatoire")
        Long recolteId,

        @NotNull(message = "L'ID de l'arbre est obligatoire")
        Long arbreId
) {}