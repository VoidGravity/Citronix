package org.abdellah.citronix.DTO.response.expermineting;


import lombok.Builder;
import lombok.Data;
import java.util.*;



@Data
@Builder
public class PerformanceViewModel {
    private int annee;
    private double productionTotale;
    private double revenuTotal;
    private double rendementMoyen;
    private List<PerformanceSaisonniereViewModel> performancesParSaison;
}

