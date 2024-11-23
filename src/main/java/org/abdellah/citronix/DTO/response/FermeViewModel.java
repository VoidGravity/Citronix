package org.abdellah.citronix.DTO.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FermeViewModel {
    private Long id;
    private String nom;
    private String localisation;
    private double superficie;
    private LocalDate dateCreation;
    private int nombreChamps;
    private double superficieUtilisee;
    private double superficieDisponible;
    private int nombreTotalArbres;
}