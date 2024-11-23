package org.abdellah.citronix.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RecolteViewModel {
    private Long id;
    private LocalDate dateRecolte;
    private String saison;
    private double quantiteTotale;
    private int nombreArbresRecoltes;
    private double rendementMoyen;
    private List<VenteSimpleViewModel> ventes;
}