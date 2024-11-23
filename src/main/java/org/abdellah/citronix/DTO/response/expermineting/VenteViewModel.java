package org.abdellah.citronix.DTO.response.expermineting;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VenteViewModel {
    private Integer id;
    private LocalDate dateVente;
    private Double prixUnitaire;
    private String client;
    private Double quantiteVendue;
    private Double revenuTotal;
    private String recolteReference; // e.g., "Récolte Été 2024"
}