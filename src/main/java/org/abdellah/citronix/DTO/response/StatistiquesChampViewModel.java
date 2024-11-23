package org.abdellah.citronix.DTO.response;


import lombok.Builder;
import lombok.Data;
import java.util.*;


@Data
@Builder
public class StatistiquesChampViewModel {
    private String nomChamp;
    private double superficie;
    private int nombreArbres;
    private double densiteArbres;
    private double productionMoyenne;
    private Map<String, Double> productionParSaison;
}