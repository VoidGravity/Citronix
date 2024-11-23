package org.abdellah.citronix.DTO.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerformanceSaisonniereViewModel {
    private String saison;
    private double production;
    private double revenu;
    private double prixMoyen;
}