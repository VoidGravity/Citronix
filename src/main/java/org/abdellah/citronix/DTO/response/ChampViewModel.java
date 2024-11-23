package org.abdellah.citronix.DTO.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChampViewModel {
    private Long id;
    private String nom;
    private Double superficie;
    private String fermeName;
    private int nombreArbres;
    private int capaciteMaxArbres; // Based on 100 trees per hectare
    private int placesDisponibles;
    private double tauxOccupation; // percentage of used capacity
}
