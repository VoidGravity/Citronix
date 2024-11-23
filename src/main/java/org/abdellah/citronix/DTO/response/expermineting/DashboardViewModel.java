package org.abdellah.citronix.DTO.response.expermineting;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class DashboardViewModel {
    private int totalArbres;
    private int arbresProductifs;
    private double superficieTotale;
    private double revenusAnnuels;
    private Map<String, Double> productionParSaison;
    private List<ProductiviteParAgeViewModel> productiviteParAge;
}
